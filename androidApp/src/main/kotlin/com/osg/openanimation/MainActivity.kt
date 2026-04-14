package com.osg.openanimation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.osg.openanimation.core.ui.AppEntry
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin<PreviewApplication>{
            modules(androidActivityModule)
        }

        setContent {
            AppEntry()
        }
    }
}

val Activity.androidActivityModule: Module
    get() = module {
        single<Activity> { this@androidActivityModule } bind Context::class
    }