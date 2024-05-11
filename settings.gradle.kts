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
include(":feature-advertisements")
include(":feature-create-rent")
include(":feature-favorites")
include(":feature-home")
include(":feature-product-details")
include(":feature-profile")
include(":feature-rents")
