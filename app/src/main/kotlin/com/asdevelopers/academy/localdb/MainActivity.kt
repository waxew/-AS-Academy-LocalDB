package com.asdevelopers.academy.localdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.asdevelopers.academy.core.ui.AcademyCourseApp

/**
 * نقطه ورود اپ LocalDB عمداً سبک است.
 * Navigation، Progress، Quiz، Exercise و UI عمومی از AS-Academy-Core می‌آیند.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AcademyCourseApp(courseId = LocalDbCourseConfig.COURSE_ID)
        }
    }
}
