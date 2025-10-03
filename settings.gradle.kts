pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ciber Modelo Application"
include(":app")
include(":base")
include(":framework:requestmanager")
include(":data")
include(":domain")
include(":framework:databasemanager")
