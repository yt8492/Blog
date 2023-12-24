plugins {
    kotlin("js")
}

group = "org.yt8492"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion = "2.3.7"
    implementation(project(":common"))
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:18.0.0-pre.332-kotlin-1.6.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.0.0-pre.332-kotlin-1.6.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.3.0-pre.332-kotlin-1.6.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.332-kotlin-1.6.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled-next:1.1.0-pre.332-kotlin-1.6.21")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("com.soywiz.korlibs.klock:klock:4.0.10")
    implementation(npm("react-markdown", "5.0.3"))
    implementation(npm("remark-gfm", "1.0.0"))
    implementation(npm("react-syntax-highlighter", "15.4.3"))
}

kotlin {
    js(IR) {
        browser {
            webpackTask {
                cssSupport {
                    enabled.set(true)
                }
                outputFileName = "main.js"
            }

            runTask {
                cssSupport {
                    enabled.set(true)
                }
                outputFileName = "main.js"
            }
        }
        binaries.executable()
    }
}
