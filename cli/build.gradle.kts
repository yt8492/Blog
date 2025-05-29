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
            compilations.all {
                kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
            }
        }
    }

    sourceSets {
        val macosX64Main by getting {
            dependencies {
                val ktorVersion = "2.3.7"
                implementation(project(":common"))
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
                implementation("io.ktor:ktor-client-curl:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            }
        }
    }
}
