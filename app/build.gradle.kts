import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.internal.de.undercouch.gradle.tasks.download.Download

plugins{
    id("java")

    kotlin("jvm") version libs.versions.kotlin
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.serialization)

    id("de.undercouch.download") version "5.6.0"
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
        }
        kotlin{
            srcDirs("src")
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
            includeAllModules = true
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Processing"
            packageVersion = rootProject.version as String

            macOS{
                bundleID = "org.processingfoundation.processing.app"
                iconFile = project.file("../build/macos/processing.icns")
            }
            windows{
                iconFile = project.file("../build/windows/processing.ico")
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

val compottieVersion = "2.0.0-rc02"

dependencies {
    implementation("com.formdev:flatlaf:3.4.1")

    implementation("net.java.dev.jna:jna:5.12.1")
    implementation("net.java.dev.jna:jna-platform:5.12.1")

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

    implementation("io.github.alexzhirkevich:compottie:${compottieVersion}")

    implementation("com.charleskorn.kaml:kaml:0.65.0")
}

tasks.register<Copy>("addCore"){
    dependsOn(project(":core").tasks.jar)
    from("../core/build/libs/")
    include("*.jar")
    into("build/resources/main/core/library")
}
tasks.jar { dependsOn("addCore") }
tasks.processResources{ finalizedBy("addCore") }

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

    dest(layout.buildDirectory.file("jdk-$platform-$arch.tar.gz"))
    overwrite(false)
}
tasks.register<Copy>("unzipJDK") {
    val dl = tasks.findByPath("downloadJDK") as Download
    dependsOn(dl)
    from(tarTree(dl.dest))
    into(layout.buildDirectory.dir("resources-bundled/common"))
}
afterEvaluate {
    tasks.findByName("prepareAppResources")?.dependsOn("unzipJDK")
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
