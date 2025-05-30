package com.osg.openanimation.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.osg.core.ui.util.icons.brandingpack.LogoVector

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.Companion.padding(12.dp)) {
        Image(imageVector = LogoVector, contentDescription = "")
    }
}