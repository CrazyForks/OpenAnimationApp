package com.osg.openanimation.preview.upload

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.osg.openanimation.core.data.upload.ModerationStatus
import com.osg.openanimation.core.data.upload.UploadedAnimationMeta
import com.osg.openanimation.core.ui.color.model.ColorsEditPalette
import com.osg.openanimation.core.ui.components.lottie.AnimationDataState
import com.osg.openanimation.core.ui.dashboard.AnimationUploadForm

@PreviewScreenSizes
@Composable
private fun AnimationUploadFormPreview(

) {
    AnimationUploadForm(
        modifier = Modifier,
        editableAnimation = ColorsEditPalette(
            processedJsonState = AnimationDataState.Processing,
            options = listOf(
                listOf(
                    Color(0xFF000000),
                    Color(0xFFFFFFFF),
                    Color(0xFFFF0000),
                    Color(0xFF00FF00),
                ),
                listOf(
                    Color(0xFF000000),
                    Color(0xFFFFFFFF),
                    Color(0xFFFF0000),
                    Color(0xFF00FF00),
                )
            ),
        ),
        uploadedAnimationMeta = UploadedAnimationMeta(
            name = "Sample Animation",
            tags = listOf("tag1", "tag2"),
            hash = "samplehash",
            uploadTimestamp = 0L,
            description = "An example description for the sample animation.",
            path = "/path/to/animation.json",
            isSubmitted = false
        ),
        isUnsaved = true,
        moderationStatus = ModerationStatus.DRAFT,
        allTags = setOf("tag1", "tag2", "tag3", "example"),
        onPaletteSelected = {},
        onUploadClick = {},
        onTitleChanged = {},
        onTagsChanged = {},
        onRemovalClick = {},
        onSaveClick = {}
    )
}