package com.osg.openanimation

import androidx.compose.ui.window.ComposeUIViewController
import com.osg.openanimation.core.ui.AppEntry
import org.koin.plugin.module.dsl.startKoin
import platform.UIKit.UIViewController


@Suppress("unused", "FunctionName")
fun MainViewController(): UIViewController {
    startKoin<PreviewApplication>()
    return ComposeUIViewController {
        AppEntry()
    }
}