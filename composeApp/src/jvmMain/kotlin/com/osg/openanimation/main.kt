package com.osg.openanimation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.osg.openanimation.core.ui.AppEntry
import com.osg.openanimation.core.ui.util.icons.brandingpack.LogoVector
import org.koin.plugin.module.dsl.startKoin


fun main() = application {
    startKoin<PreviewApplication>()
    Window(
        icon = rememberVectorPainter(image = LogoVector),
        state = rememberWindowState(
            placement = WindowPlacement.Maximized
        ),
        onCloseRequest = ::exitApplication,
        title = "OpenAnimation",
    ) {

        window.background = java.awt.Color(Color.Green.toArgb())

       AppEntry()
    }
}