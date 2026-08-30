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
include(":engine")
project(":engine").projectDir = file("academy-core/engine")
include(":core")
project(":core").projectDir = file("academy-core/core")
