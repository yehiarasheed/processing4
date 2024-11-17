rootProject.name = "processing"
include(
    "core",
    "core:different",
    "app",
    "java"
)

buildscript {
    repositories {
        mavenCentral()
    }
}