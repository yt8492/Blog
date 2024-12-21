import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.shadow)
    application
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    withType<Sync> {
        println(tasks)
        dependsOn(":webfront:jsBrowserProductionWebpack")
        into("generated") {
            from("${rootProject.project(":webfront").layout.buildDirectory.asFile.get()}/kotlin-webpack/js/productionExecutable")
        }
    }
    withType<Zip> {
        println("tasks: $tasks")
        dependsOn(":webfront:jsBrowserProductionWebpack")
        into("generated") {
            from("${rootProject.project(":webfront").layout.buildDirectory.asFile.get()}/kotlin-webpack/js/productionExecutable")
        }
    }
}

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/ktor")
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

sourceSets["main"].resources.srcDir("generated")

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.html)
    implementation(libs.korlibs.time)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.callLogging)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.htmlBuilder)
    implementation(libs.ktor.server.statusPages)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.koin.ktor)
    implementation(libs.google.cloud.datastore)

    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.property)
}
