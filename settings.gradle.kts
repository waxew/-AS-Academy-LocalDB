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
    versionCatalogs {
        create("libs") {
            from(files("academy-core/gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "AS-Academy-LocalDB"
include(":app")
include(":academy-course")

// ماژول‌های مشترک مستقیماً از Core مرکزی استفاده می‌شوند.
include(":course")
project(":course").projectDir = file("academy-core/course")
include(":core")
project(":core").projectDir = file("academy-core/core")

// UI مشترک از AS-Academy-MainUi می‌آید؛ Course App نباید UI را fork کند.
include(":main-ui")
project(":main-ui").projectDir = file("academy-main-ui/main-ui")
