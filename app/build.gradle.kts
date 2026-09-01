plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

// MainCourse منبع واحد محتوای آموزشی است. Course Package مرکزی در زمان Build
// به قرارداد assets فعلی Core یعنی course/localdb نگاشت می‌شود تا Loader و IDهای Progress نشکنند.
val mainCoursePackageDir = rootProject.file("academy-main-course/courses/localdb/course")
val generatedCourseAssetsDir = file("$buildDir/generated/courseAssets")
val syncCourseAssets by tasks.registering(Copy::class) {
    doFirst {
        require(mainCoursePackageDir.resolve("manifest.json").isFile) {
            "LocalDB MainCourse package is missing: ${mainCoursePackageDir.path}. Initialize academy-main-course before building."
        }
    }
    from(mainCoursePackageDir)
    into(file("$generatedCourseAssetsDir/course/localdb"))
}

// اطلاعات امضای Release فقط از محیط CI/ماشین توسعه خوانده می‌شود و داخل Git ذخیره نمی‌شود.
val releaseStoreFile = System.getenv("AS_RELEASE_STORE_FILE")
val releaseStorePassword = System.getenv("AS_RELEASE_STORE_PASSWORD")
val releaseKeyAlias = System.getenv("AS_RELEASE_KEY_ALIAS")
val releaseKeyPassword = System.getenv("AS_RELEASE_KEY_PASSWORD")
val hasReleaseSigning = listOf(
    releaseStoreFile,
    releaseStorePassword,
    releaseKeyAlias,
    releaseKeyPassword
).all { !it.isNullOrBlank() }

android {
    namespace = "com.asdevelopers.academy.localdb"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.asdevelopers.academy.localdb"
        minSdk = 23
        targetSdk = 37
        // نسخه 0.3.0: Build پایدار + گسترش محتوای تخصصی و QA.
        versionCode = 3
        versionName = "0.3.0"
    }

    signingConfigs {
        if (hasReleaseSigning) {
            create("release") {
                storeFile = file(releaseStoreFile!!)
                storePassword = releaseStorePassword
                keyAlias = releaseKeyAlias
                keyPassword = releaseKeyPassword
                enableV1Signing = true
                enableV2Signing = true
                enableV3Signing = true
                enableV4Signing = true
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            if (hasReleaseSigning) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    buildFeatures { compose = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Loader مرکزی در Runtime همچنان course/localdb را می‌خواند؛ منبع فایل‌ها MainCourse است.
    sourceSets.getByName("main").assets.srcDir(generatedCourseAssetsDir)
}

// قبل از Merge شدن Assets، Course Package مرکزی آماده می‌شود.
tasks.named("preBuild").configure { dependsOn(syncCourseAssets) }

dependencies {
    implementation(project(":core"))
    implementation(project(":course"))
    implementation(project(":academy-course"))
    // Presentation عمومی اپ از MainUi مصرف می‌شود؛ Core همچنان Runtime/Engine است.
    implementation(project(":main-ui"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
}
