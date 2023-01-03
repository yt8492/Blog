import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    withType<Test> {
        useJUnitPlatform()
    }
    withType<Sync> {
        dependsOn(":webfront:browserProductionWebpack")
        into("generated") {
            from("${rootProject.project(":webfront").buildDir}/distributions")
        }
    }
    withType<Zip> {
        dependsOn(":webfront:browserProductionWebpack")
        into("generated") {
            from("${rootProject.project(":webfront").buildDir}/distributions")
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
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
    implementation("com.soywiz.korlibs.klock:klock:2.2.0")
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("io.ktor:ktor-server-netty:1.6.1")
    implementation("io.ktor:ktor-serialization:1.6.1")
    implementation("io.ktor:ktor-server-core:1.6.1")
    implementation("io.ktor:ktor-html-builder:1.6.1")
    implementation("io.ktor:ktor-auth:1.6.1")
    implementation("io.ktor:ktor-auth-jwt:1.6.1")
    implementation("io.insert-koin:koin-ktor:2.2.3")
    implementation("com.google.cloud:google-cloud-datastore:2.13.0")

    testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
    testImplementation("io.kotest:kotest-assertions-core:5.5.4")
    testImplementation("io.kotest:kotest-property:5.5.4")
}
