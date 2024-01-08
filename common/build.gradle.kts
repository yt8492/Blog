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
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
                implementation("com.soywiz.korlibs.krypto:krypto:4.0.10")
                implementation("com.soywiz.korlibs.klock:klock:4.0.10")
            }
        }
        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}
