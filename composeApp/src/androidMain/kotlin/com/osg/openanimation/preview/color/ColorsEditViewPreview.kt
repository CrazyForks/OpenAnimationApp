package com.osg.openanimation.preview.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.osg.openanimation.core.ui.color.ui.ColorPaletteOptionsView

@Preview
@Composable
fun ColorsEditViewPreview() {
    Scaffold(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .height(400.dp)
    ){
        Column {
            ColorsEditView(
                modifier = Modifier.padding(it),
            )
        }

    }
}

@Composable
fun ColorsEditView(
    modifier: Modifier = Modifier,
) {

    ColorPaletteOptionsView(
        modifier = modifier,
        transformOptions = listOf(
            listOf(
                Color(0xFF000000),
                Color(0xFFFFFFFF),
                Color(0xFFFF0000),
                Color(0xFF00FF00),
            ),
            listOf(
                Color(0xffff9a9e),
                Color(0xFFFFFFFF),
                Color(0xff0059ff),
                Color(0xff238d91),
            )
        ),
        onPalletSelect = {},
        selectedIndex = 0,
    )
}