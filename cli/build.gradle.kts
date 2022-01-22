import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = "com.yt8492"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
}

kotlin {
    macosX64()
    targets.filterIsInstance<KotlinNativeTarget>().forEach {
        with(it) {
            binaries {
                executable {
                    entryPoint = "com.yt8492.blog.cli.main"
                }
            }
            compilations["main"].enableEndorsedLibs = true
            compilations.all {
                kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
            }
        }
    }

    sourceSets {
        val macosX64Main by getting {
            dependencies {
                implementation(project(":common"))
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.1")
                implementation("io.ktor:ktor-client-curl:1.5.4")
                implementation("io.ktor:ktor-client-serialization:1.5.4")
            }
        }
    }
}
