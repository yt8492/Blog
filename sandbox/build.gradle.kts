plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

application {
    mainClassName = ("com.yt8492.blog.sandbox.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation(project(":server"))
    implementation(kotlin("stdlib"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("com.google.cloud:google-cloud-datastore:2.1.0")
}