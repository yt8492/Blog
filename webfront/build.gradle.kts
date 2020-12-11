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
    implementation("org.jetbrains:markdown-js:0.2.0.pre-55")
    implementation("io.ktor:ktor-client-js:1.4.1")
    implementation("io.ktor:ktor-client-serialization:1.4.1")
}

kotlin {
    js {
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
