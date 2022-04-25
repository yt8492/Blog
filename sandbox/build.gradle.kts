plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":server"))
    implementation(kotlin("stdlib"))
}