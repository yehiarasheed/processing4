plugins {
    id("java")
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
            exclude("processing/mode/java/preproc/**")
        }
    }
    test{
        java{
            srcDirs("test")
        }
    }
}

dependencies{
    implementation(project(":app"))
    implementation(project(":core"))
    implementation(project(":java:preprocessor"))

    implementation(libs.eclipseJDT)
    implementation(libs.classpathExplorer)
    implementation(libs.netbeansSwing)
    implementation(libs.ant)
    implementation(libs.lsp4j)
    implementation(libs.jsoup)
    implementation(libs.antlr)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
}

tasks.compileJava{
    options.encoding = "UTF-8"
}

// TODO: This is a temporary workaround until the resources are properly handled
tasks.register<Copy>("extraResources"){
    from(".")
    include("keywords.txt")
    into("build/resources/main")
}
tasks.jar { dependsOn("extraResources") }
tasks.processResources{ finalizedBy("extraResources") }
tasks.compileTestJava{ finalizedBy("extraResources") }

tasks.test {
    useJUnit()
}