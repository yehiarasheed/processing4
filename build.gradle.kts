group = "org.processing"

// Grab the latest version from the GitHub API
val apiUrl = "https://api.github.com/repos/processing/processing4/releases"
val response = java.net.URL(apiUrl).readText()
val tagName = response.substringAfter("\"tag_name\":\"").substringBefore("\"")
val currentVersion = tagName.split("-").last()
version = currentVersion.split(".").let { parts ->
    "${parts[0]}.${parts[1]}.${parts[2].toInt() + 1}"
}

plugins {
    kotlin("jvm") version libs.versions.kotlin apply false
    alias(libs.plugins.kotlinMultiplatform) apply false

    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
}