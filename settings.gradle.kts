rootProject.name = "Blog"
include("common", "server", "webfront", "cli", "sandbox")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
