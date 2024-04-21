plugins {
    kotlin("multiplatform")
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
                val ktorVersion = "2.3.7"
                implementation(project(":common"))
                implementation(kotlin("stdlib-js"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.668")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.668")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.20.1-pre.668")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.668")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled-next:1.2.3-pre.668")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("com.soywiz.korlibs.klock:klock:4.0.10")

                implementation(npm("react-markdown", "9.0.1"))
                implementation(npm("remark-gfm", "4.0.0"))
                implementation(npm("react-syntax-highlighter", "15.5.0"))
            }
        }
    }
}
