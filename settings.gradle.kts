pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "MovieApp"
include(":app")
include(":core:designsystem")
include(":core:model")
include(":core:ui")
include(":core:data")
include(":core:testing")
include(":core:androidtest")
include(":core:domain")
include(":core:network")
include(":core:common")
include(":feature:home")
include(":feature:search")
