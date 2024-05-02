rootProject.name = "Rendo"
include(":composeApp")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":core")
include(":core-favorites")
include(":feature-favorites")
include(":feature-home")
include(":feature-product-details")
