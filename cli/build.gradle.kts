import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.yt8492"
version = "1.0.0"

repositories {
    mavenCentral()
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
                compileTaskProvider.configure {
                    compilerOptions {
                        optIn.add("kotlin.RequiresOptIn")
                    }
                }
            }
        }
    }

    sourceSets {
        val macosX64Main by getting {
            dependencies {
                implementation(project(":common"))
                implementation(kotlin("stdlib-common"))
                implementation(libs.kotlinx.cli)
                implementation(libs.ktor.client.curl)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.contentNegotiation)
            }
        }
    }
}
