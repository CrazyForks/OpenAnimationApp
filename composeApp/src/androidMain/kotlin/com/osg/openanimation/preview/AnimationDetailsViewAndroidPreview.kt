package com.osg.openanimation.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.osg.openanimation.core.ui.details.AnimationDetailsPanes
import com.osg.openanimation.core.ui.details.AnimationDetailsView
import com.osg.openanimation.core.ui.details.DetailsUiPane
import com.osg.openanimation.core.ui.di.UserSessionState
import com.osg.openanimation.core.ui.theme.TrueTheme
import com.osg.openanimation.core.data.animation.AnimationMetadata
import com.osg.openanimation.core.data.stats.AnimationStats
import com.osg.openanimation.repo.AnimationDataCollection
import com.osg.openanimation.repo.fromLocaleStorage


@PreviewScreenSizes
@Preview(showSystemUi = true)
@Composable
fun AnimationDetailsPanesAndroidPreview() {
    TrueTheme{
        val item = AnimationDataCollection.ORBITING_DOTS
        AnimationDetailsPanes(
            modifier = Modifier.fillMaxSize(),
            detailsUiState = DetailsUiPane(
                animationState = item.fromLocaleStorage(),
                metadata = item.metadata,
                animationStats = AnimationStats(

                ),
            ),
            onLikeClick = {},
            onDownloadClick = {},
            onTagClick = {},
            relatedAnimations = generateAnimationUiDataList().take(4),
            onRelatedAnimationClicked = {},
            onDismissSignInDialog = {},
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun AnimationDetailsViewAndroidPreview() {
    TrueTheme {
        val item = AnimationDataCollection.DAY_NIGHT_CYCLE
        AnimationDetailsView(
            detailsUiState = DetailsUiPane(
                animationState = item.fromLocaleStorage(),
                metadata = AnimationMetadata(
                    name = "Animation",
                    hash = "fvfssfv",
                    tags = setOf("tag1", "tag2")
                ),
                animationStats = AnimationStats(

                ),
            ),
            onLikeClick = {},
            onDownloadClick = {},
            onDismissSignInDialog = {},
            onTagClick = {},
        )
    }
}