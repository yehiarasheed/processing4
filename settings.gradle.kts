rootProject.name = "processing"
include(
    "core",
    "core:different",
    "app"
)

pluginManagement {
    plugins {
        kotlin("jvm") version "1.9.23"
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
}