plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.asdevelopers.academy.localdb"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.asdevelopers.academy.localdb"
        minSdk = 23
        targetSdk = 37
        // نسخه 0.2.0 مرحله تثبیت Build و بسته آموزشی است.
        versionCode = 2
        versionName = "0.2.0"
    }

    buildFeatures { compose = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Loader مرکزی مسیر course/localdb را از assets می‌خواند.
    sourceSets.getByName("main").assets.srcDir("..")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":course"))
    implementation(project(":academy-course"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
}
