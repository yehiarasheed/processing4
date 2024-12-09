rootProject.name = "processing"
include(
    "core",
    "core:different",
    "app",
    "java",
    "java:preprocessor",
    "java:lsp"
)

buildscript {
    repositories {
        mavenCentral()
    }
}