plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "org.yt8492"
version = "1.0.0"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
                outputFileName = "main.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(kotlin("stdlib-js"))
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlin.react)
                implementation(libs.kotlin.react.dom)
                implementation(libs.kotlin.react.router)
                implementation(libs.kotlin.react.router.dom)
                implementation(libs.kotlin.remix.run.router)
                implementation(libs.kotlin.css)
                implementation(libs.kotlin.emotion)
                implementation(libs.kotlin.js)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.contentNegotiation)
                implementation(libs.korlibs.time)

                implementation(npm("react-markdown", "9.0.1"))
                implementation(npm("remark-gfm", "4.0.0"))
                implementation(npm("react-syntax-highlighter", "15.5.0"))
                implementation(npm("webpack-node-externals", "3.0.0"))
            }
        }
    }
}
