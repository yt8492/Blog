plugins {
    kotlin("js")
}

group = "org.yt8492"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    maven("https://dl.bintray.com/jetbrains/markdown")
}

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.4.2")
    implementation("org.jetbrains:kotlin-react:17.0.0-pre.129-kotlin-1.4.21")
    implementation("org.jetbrains:kotlin-react-dom:17.0.0-pre.129-kotlin-1.4.21")
    implementation("org.jetbrains:kotlin-react-router-dom:5.2.0-pre.129-kotlin-1.4.21")
    implementation("org.jetbrains:kotlin-css:1.0.0-pre.131-kotlin-1.4.21")
    implementation("org.jetbrains:kotlin-styled:5.2.0-pre.130-kotlin-1.4.21")
    implementation("io.ktor:ktor-client-js:1.4.1")
    implementation("io.ktor:ktor-client-serialization:1.4.1")
    implementation("com.soywiz.korlibs.klock:klock:2.0.0-alpha")
    implementation(npm("react-markdown", "5.0.3"))
    implementation(npm("remark-gfm", "1.0.0"))
    implementation(npm("react-syntax-highlighter", "15.4.3"))
}

kotlin {
    js {
        browser {
            webpackTask {
                cssSupport.enabled = true
                outputFileName = "main.js"
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
                outputFileName = "main.js"
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}
