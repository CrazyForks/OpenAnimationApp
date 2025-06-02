package com.osg.openanimation.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.osg.openanimation.core.ui.components.report.AnimationReportContent
import com.osg.openanimation.core.data.animation.AnimationMetadata

/**
 * Preview for Animation Report Dialog
 */
@Preview(
    name = "Animation Report Dialog",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun AnimationReportPreview() {
    val sampleMetadata = AnimationMetadata(
        name = "Sample Animation",
        author = "John Doe",
        description = "This is a sample animation for preview purposes",
        hash = "abc123hash456",
        tags = setOf("Preview", "Sample", "Testing"),
    )
    
    AnimationReportContent(
        animationMetadata = sampleMetadata,
        onReportClick = { /* Preview only */ },
        onDismiss = { /* Preview only */ }
    )
}