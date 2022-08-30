plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("kapt") version "1.7.10"
}

dependencies {
    implementation("com.squareup:kotlinpoet:1.4.1")
    implementation("com.squareup:kotlinpoet-metadata:1.7.1")
    implementation ("com.google.auto.service:auto-service:1.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")

    implementation(project(":annotation"))
    kapt ("com.google.auto.service:auto-service:1.0")
    implementation(kotlin("reflect"))
}