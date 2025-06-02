package com.osg.openanimation.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.osg.openanimation.core.ui.details.DetailsScreenStates
import com.osg.openanimation.repo.AnimationDataCollection
import com.osg.openanimation.repo.fromLocaleStorage
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun AnimationDetailsPanesAndroidShimmerPreview() {
    var isClicked by remember { mutableStateOf(false) }
    var isLike by remember { mutableStateOf(true) }
    TrueTheme{
        val detailsScreenState = DetailsScreenStates.Success(
            detailsUiPane = DetailsUiPane(
                isLiked = isLike,
                animationUiData = generateAnimationUiDataList().last(),
                animationStats = AnimationStats(
                    downloadCount = 5,
                    likeCount = 10,
                ),
            ),
            relatedAnimations = generateAnimationUiDataList().take(4),
        )
        AnimationDetailsPanes(
            modifier = Modifier.fillMaxSize().statusBarsPadding(),
            detailsUiState = detailsScreenState,
            onLikeClick = {
                isClicked.not().also {
                    isClicked = it
                }
            },
            onDownloadClick = {},
            onTagClick = {},
            onRelatedAnimationClicked = {},
            onDismissSignInDialog = {},
        )

        LaunchedEffect(isClicked) {
            if (isClicked) {
                delay(700)
                isClicked = false
                isLike = !isLike
            }
        }
    }
}

@PreviewScreenSizes
@Preview(showSystemUi = true)
@Composable
fun AnimationDetailsPanesAndroidPreview() {
    TrueTheme{
        val detailsScreenState = DetailsScreenStates.Success(
            detailsUiPane = DetailsUiPane(
                animationUiData = generateAnimationUiDataList().last(),
                animationStats = AnimationStats(
                    downloadCount = 5,
                    likeCount = 10,
                ),
            ),
            relatedAnimations = generateAnimationUiDataList().take(4),
        )
        AnimationDetailsPanes(
            modifier = Modifier.fillMaxSize(),
            detailsUiState = detailsScreenState,
            onLikeClick = {
                true
            },
            onDownloadClick = {},
            onTagClick = {},
            onRelatedAnimationClicked = {},
            onDismissSignInDialog = {},
        )
    }
}