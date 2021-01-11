plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

group = "org.yt8492"
version = "1.0.0"

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    jvm()
    js(IR) {
        browser()
    }
    linuxX64()
    macosX64()
    mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
                implementation("com.soywiz.korlibs.krypto:krypto:2.0.0-alpha")
                implementation("com.soywiz.korlibs.klock:klock:2.0.0-alpha")
            }
        }
        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}
