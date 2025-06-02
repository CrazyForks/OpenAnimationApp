package com.osg.openanimation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.osg.openanimation.core.ui.details.CopyLinkToClipboardButton
import com.osg.openanimation.core.ui.details.DownloadButton

@Preview(showBackground = true)
@Composable
fun DownloadButtonIdlePreview() {
    var isDownloadedTransitionState by remember { mutableStateOf(false) }
    DownloadButton(
        isDownloadedTransition = isDownloadedTransitionState,
        onClick = {
            isDownloadedTransitionState = !isDownloadedTransitionState
        }
    )
    LaunchedEffect(isDownloadedTransitionState) {
        if (isDownloadedTransitionState) {
            // Simulate a delay for the download transition
            kotlinx.coroutines.delay(2000)
            isDownloadedTransitionState = false // Reset to idle state after transition
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CopyLinkToClipboardButtonPreview() {
    CopyLinkToClipboardButton(
        generateLink = { "https://example.com" }
    )
}