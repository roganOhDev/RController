plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("kapt") version "1.7.10"
    application
}

group = "me.ohdonggeun"
version = "1.0-SNAPSHOT"

dependencies {
    testImplementation(kotlin("test"))
    implementation ("com.google.code.gson:gson:2.8.5")

    implementation(project(":annotation"))
    kapt (project(":processor"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}

kapt {
    showProcessorStats = true
    useBuildCache = false
}
