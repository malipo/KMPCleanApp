enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KMPCleanApp"
include(":androidApp")
include(":shared")
include(":kmpCore:network")
include(":kmpFeatures:museumList")
include(":tv")

