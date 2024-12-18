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
tasks.withType<JavaExec> {
    systemProperty("processing.version", version)
    systemProperty("processing.revision", "1296")
}


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
    }
}

compose.desktop {
    application {
        mainClass = "processing.app.ui.Start"

        nativeDistributions{
            modules("jdk.jdi", "java.compiler")
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Processing"
            packageVersion = rootProject.version as String

            macOS{
                bundleID = "org.processing.app"
                iconFile = project.file("../build/macos/processing.icns")
                infoPlist{
                    extraKeysRawXml = plistStrings
                }
                entitlementsFile.set(project.file("entitlements.plist"))
                runtimeEntitlementsFile.set(project.file("entitlements.plist"))
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

// LEGACY ACTIONS
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
        exclude("processing-examples-main/README.md")
        exclude("processing-examples-main/.github/**")
        eachFile { relativePath = RelativePath(true, *relativePath.segments.drop(1).toTypedArray()) }
        includeEmptyDirs = false
    }
    into(layout.buildDirectory.dir("resources-bundled/common/modes/java/examples"))
}
tasks.register<Download>("downloadProcessingWebsiteExamples") {
    src("https://github.com/processing/processing-website/archive/refs/heads/main.zip")
    dest(layout.buildDirectory.file("tmp/processing-website.zip"))
    overwrite(false)
}
tasks.register<Copy>("unzipWebsiteExamples") {
    val dl = tasks.findByPath("downloadProcessingWebsiteExamples") as Download
    dependsOn(dl)
    dependsOn("unzipExamples")
    print(dl.dest)
    from(zipTree(dl.dest)){
        include("processing-website-main/content/examples/**")
        eachFile { relativePath = RelativePath(true, *relativePath.segments.drop(3).toTypedArray()) }
        includeEmptyDirs = false
    }
    into(layout.buildDirectory.dir("resources-bundled/common/modes/java/examples"))
}
tasks.register<Copy>("copyJavaMode"){
    dependsOn("unzipExamples","unzipWebsiteExamples")
    dependsOn(project(":java").tasks.named("extraResources"))
    from(project(":java").layout.buildDirectory.dir("resources-bundled"))
    into(layout.buildDirectory.dir("resources-bundled"))
}
tasks.register<Copy>("renameWindres") {
    dependsOn("copyJavaMode", "copyShared", "unzipJDK")
    val dir = layout.buildDirectory.dir("resources-bundled/common/modes/java/application/launch4j/bin/")
    val os: OperatingSystem = DefaultNativePlatform.getCurrentOperatingSystem()
    val platform = when {
        os.isWindows -> "windows"
        os.isMacOsX -> "macos"
        else -> "linux"
    }
    from(dir) {
        include("*-$platform*")
        rename("(.*)-$platform(.*)", "$1$2")
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    into(dir)
}
afterEvaluate {
    tasks.findByName("prepareAppResources")?.dependsOn("unzipJDK","copyShared", "copyCore", "unzipExamples","renameWindres", "copyJavaMode")
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

val plistStrings: String
    get() = """
    <key>CFBundleURLTypes</key>
    <array>
        <dict>
            <key>CFBundleURLName</key>
            <string>org.processing.app</string>
            <key>CFBundleURLSchemes</key>
            <array>
                <string>pde</string>
            </array>
        </dict>
    </array>
    <key>CFBundleDocumentTypes</key>
    <array>
        <dict>
            <key>CFBundleTypeExtensions</key>
            <array>
                <string>pde</string>
            </array>
            <key>LSTypeIsPackage</key>
            <false/>
            <key>CFBundleTypeIconFile</key>
            <string>macos/pde.icns</string>
            <key>CFBundleTypeName</key>
            <string>Processing Source Code</string>
            <key>CFBundleTypeRole</key>
            <string>Editor</string>
        </dict>
        <dict>
            <key>CFBundleTypeExtensions</key>
            <array>
                <string>pyde</string>
            </array>
            <key>LSTypeIsPackage</key>
            <false/>
            <key>CFBundleTypeIconFile</key>
            <string>macos/pde.icns</string>
            <key>CFBundleTypeName</key>
            <string>Processing Python Source Code</string>
            <key>CFBundleTypeRole</key>
            <string>Editor</string>
        </dict>
        <dict>
            <key>CFBundleTypeExtensions</key>
            <array>
                <string>pdez</string>
            </array>
            <key>LSTypeIsPackage</key>
            <false/>
            <key>CFBundleTypeIconFile</key>
            <string>macos/pdez.icns</string>
            <key>CFBundleTypeName</key>
            <string>Processing Sketch Bundle</string>
            <key>CFBundleTypeRole</key>
            <string>Editor</string>
        </dict>
        <dict>
            <key>CFBundleTypeExtensions</key>
            <array>
                <string>pdex</string>
            </array>
            <key>LSTypeIsPackage</key>
            <false/>
            <key>CFBundleTypeIconFile</key>
            <string>macos/pdex.icns</string>
            <key>CFBundleTypeName</key>
            <string>Processing Contribution Bundle</string>
            <key>CFBundleTypeRole</key>
            <string>Viewer</string>
        </dict>
    </array>
    <key>NSCameraUsageDescription</key>
    <string>The sketch you're running needs access to your video camera.</string>
    <key>NSMicrophoneUsageDescription</key>
    <string>The sketch you're running needs access to your microphone.</string>
"""