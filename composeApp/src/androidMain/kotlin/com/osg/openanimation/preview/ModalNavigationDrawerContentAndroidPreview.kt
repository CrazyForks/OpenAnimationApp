package com.osg.openanimation.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.osg.core.ui.components.bar.ModalNavigationDrawerContent
import com.osg.core.ui.theme.TrueTheme

@Preview
@Composable
private fun ModalNavigationDrawerContentAndroidPreview() {
    TrueTheme {
        ModalNavigationDrawerContent(
            currentDestination =  null,
            isDarkMode = false,
            onRailDst = {},
        )
    }
}