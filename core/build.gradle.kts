plugins {
    id("java")
    id("maven-publish")
}

group = "org.processing.core"
version = "4.3.0"

repositories {
    mavenCentral()
    maven { url = uri("https://jogamp.org/deployment/maven") }
}

sourceSets{
    main{
        java{
            srcDirs("src/processing")
        }
        resources{
            srcDirs("src")
            exclude("**/*.java")
        }
    }
}

dependencies {
    implementation("org.jogamp.gluegen:gluegen-rt:2.5.0")
    implementation("org.jogamp.jogl:jogl-all:2.5.0")

    testImplementation("junit:junit:4.13.2")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.processing"
            artifactId = "core"
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnit()
}