package com.osg.openanimation

import androidx.compose.ui.window.ComposeUIViewController

val app = getBaseApp()

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController {
    app.AppEntry()
}