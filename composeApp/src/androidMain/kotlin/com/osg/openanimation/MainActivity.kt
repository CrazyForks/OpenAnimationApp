package com.osg.openanimation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val basePreviewApp = getBaseApp(
            platformModules = listOf(
                androidActivityModule
            )
        )
        setContent {
            basePreviewApp.AppEntry()
        }
    }
}

val Activity.androidActivityModule: Module
    get() = module {
        single<Activity> { this@androidActivityModule } bind Context::class
    }