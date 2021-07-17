plugins {
    kotlin("js")
}

group = "org.yt8492"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.217-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.217-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:5.2.0-pre.217-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.217-kotlin-1.5.21")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.0-pre.217-kotlin-1.5.21")
    implementation("io.ktor:ktor-client-core:1.6.1")
    implementation("io.ktor:ktor-client-serialization:1.6.1")
    implementation("com.soywiz.korlibs.klock:klock:2.2.0")
    implementation(npm("react-markdown", "5.0.3"))
    implementation(npm("remark-gfm", "1.0.0"))
    implementation(npm("react-syntax-highlighter", "15.4.3"))
}

kotlin {
    js(IR) {
        browser {
            webpackTask {
                cssSupport.enabled = true
                outputFileName = "main.js"
            }

            runTask {
                cssSupport.enabled = true
                outputFileName = "main.js"
            }
        }
        binaries.executable()
    }
}
