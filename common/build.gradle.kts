plugins {
    kotlin("multiplatform")
}

group = "org.yt8492"
version = "1.0.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js {
        browser()
    }
    linuxX64()
    macosX64()
    mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
    }
}
