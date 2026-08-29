plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

// فقط بسته آموزشی course داخل assets اپ کپی می‌شود؛ ریشه ریپو Asset نیست.
val generatedCourseAssetsDir = file("$buildDir/generated/courseAssets")
val syncCourseAssets by tasks.registering(Copy::class) {
    from(rootProject.file("course"))
    into(file("$generatedCourseAssetsDir/course"))
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

    // Loader مرکزی در Runtime مسیر course/localdb را از assets می‌خواند.
    sourceSets.getByName("main").assets.srcDir(generatedCourseAssetsDir)
}

// قبل از Merge شدن Assets، محتوای آموزشی اختصاصی دوره آماده می‌شود.
tasks.named("preBuild").configure { dependsOn(syncCourseAssets) }

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
