plugins{
    id("java")
    kotlin("jvm") version "1.9.23"
    id("org.jetbrains.compose") version "1.6.11"
}

group = rootProject.group

repositories{
    mavenCentral()
    maven { url = uri("https://jogamp.org/deployment/maven") }
}

sourceSets{
    main{
        java{
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
    }
}

dependencies {
    implementation("com.formdev:flatlaf:3.4.1")

    implementation("net.java.dev.jna:jna:5.12.1")
    implementation("net.java.dev.jna:jna-platform:5.12.1")

    implementation(project(":core"))
    runtimeOnly(project(":java"))
}

tasks.register<Copy>("addCore"){
    dependsOn(project(":core").tasks.jar)
    from("../core/build/libs/")
    include("*.jar")
    into("build/resources/main/core/library")
}
tasks.jar { dependsOn("addCore") }
tasks.processResources{ finalizedBy("addCore") }

