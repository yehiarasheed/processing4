import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.internal.de.undercouch.gradle.tasks.download.Download

plugins{
    id("java")

    kotlin("jvm") version libs.versions.kotlin
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.serialization)

    alias(libs.plugins.download)
}

group = rootProject.group

repositories{
    mavenCentral()
    google()
    maven { url = uri("https://jogamp.org/deployment/maven") }
}

sourceSets{
    main{
        java{
            srcDirs("src")
            exclude("**/*Kt.java")
        }
        kotlin{
            srcDirs("src")
            exclude("**/*Kt.java")
        }
        resources{
            srcDirs("src","../build/shared/")
        }
    }
}

compose.desktop {
    application {
        mainClass = "processing.app.ui.Splash"

        nativeDistributions{
            modules("jdk.jdi", "java.compiler")
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Processing"
            packageVersion = rootProject.version as String

            macOS{
                bundleID = "org.processing.app"
                iconFile = project.file("../build/macos/processing.icns")
                infoPlist{

                }
            }
            windows{
                iconFile = project.file("../build/windows/processing.ico")
                menuGroup = "Processing"
                upgradeUuid = "89d8d7fe-5602-4b12-ba10-0fe78efbd602"
            }
            linux {
                iconFile = project.file("../build/linux/processing.png")
            }

            // Allow swing to use the system look and feel
            jvmArgs(
                "-Dapple.awt.application.appearance=system"
            )
            appResourcesRootDir.set(layout.buildDirectory.dir("resources-bundled"))
        }
    }
}

dependencies {
    implementation(libs.flatlaf)

    implementation(libs.jna)
    implementation(libs.jnaplatform)

    implementation(project(":core"))
    runtimeOnly(project(":java"))
    implementation(project(":java:preprocessor"))

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.ui)
    implementation(compose.components.resources)
    implementation(compose.components.uiToolingPreview)

    implementation(compose.desktop.currentOs)

    implementation(libs.compottie)
    implementation(libs.kaml)
}

tasks.register<Copy>("copyCore"){
    dependsOn(project(":core").tasks.jar)
    from(project(":core").layout.buildDirectory.dir("libs"))
    from(project(":core").configurations.runtimeClasspath)
    into(layout.buildDirectory.dir("resources-bundled/common/core/library"))
}
tasks.register<Download>("downloadJDK") {
    val os: OperatingSystem = DefaultNativePlatform.getCurrentOperatingSystem()
    val arch: String = System.getProperty("os.arch").let { originalArch ->
        when (originalArch) {
            "amd64" -> "x64"
            "x86_64" -> "x64"
            else -> originalArch
        }
    }

    val platform = when {
        os.isWindows -> "windows"
        os.isMacOsX -> "mac"
        else -> "linux"
    }
    val javaVersion = "17"
    val imageType = "jdk"

    src("https://api.adoptium.net/v3/binary/latest/" +
            "$javaVersion/ga/" +
            "$platform/" +
            "$arch/" +
            "$imageType/" +
            "hotspot/normal/eclipse?project=jdk")

    val extension = if (os.isWindows) "zip" else "tar.gz"
    dest(layout.buildDirectory.file("jdk-$platform-$arch.$extension"))
    overwrite(false)
}
tasks.register<Copy>("unzipJDK") {
    val dl = tasks.findByPath("downloadJDK") as Download
    dependsOn(dl)

    val os: OperatingSystem = DefaultNativePlatform.getCurrentOperatingSystem()
    val archive = if (os.isWindows) {
        zipTree(dl.dest)
    } else {
        tarTree(dl.dest)
    }

    from(archive){ eachFile{ permissions{  unix("755") } } }
    into(layout.buildDirectory.dir("resources-bundled/common"))
}
tasks.register<Copy>("copyShared"){
    from("../build/shared/")
    into(layout.buildDirectory.dir("resources-bundled/common"))
}
tasks.register<Download>("downloadProcessingExamples") {
    src("https://github.com/processing/processing-examples/archive/refs/heads/main.zip")
    dest(layout.buildDirectory.file("tmp/processing-examples.zip"))
    overwrite(false)
}
tasks.register<Copy>("unzipExamples") {
    val dl = tasks.findByPath("downloadProcessingExamples") as Download
    dependsOn(dl)
    from(zipTree(dl.dest)){ // remove top level directory
        eachFile { relativePath = RelativePath(true, *relativePath.segments.drop(1).toTypedArray()) }
    }
    into(layout.buildDirectory.dir("resources-bundled/common/modes/java/examples"))
}
tasks.register<Copy>("copyJavaMode"){
    dependsOn("unzipExamples")
    dependsOn(project(":java").tasks.named("extraResources"))
    from(project(":java").layout.buildDirectory.dir("resources-bundled"))
    into(layout.buildDirectory.dir("resources-bundled"))
}

afterEvaluate {
    tasks.findByName("prepareAppResources")?.dependsOn("unzipJDK","copyShared", "copyCore", "unzipExamples")
    tasks.register("setExecutablePermissions") {
        description = "Sets executable permissions on binaries in Processing.app resources"
        group = "compose desktop"

        doLast {
            val resourcesPath = layout.buildDirectory.dir("compose/binaries")
            fileTree(resourcesPath) {
                include("**/resources/**/bin/**")
                include("**/resources/**/*.sh")
                include("**/resources/**/*.dylib")
                include("**/resources/**/*.so")
                include("**/resources/**/*.exe")
            }.forEach { file ->
                if (file.isFile) {
                    file.setExecutable(true, false)
                }
            }
        }
    }
    tasks.findByName("createDistributable")?.finalizedBy("setExecutablePermissions")
}
