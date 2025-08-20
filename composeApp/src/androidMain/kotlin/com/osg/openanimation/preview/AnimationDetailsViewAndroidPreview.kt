package com.osg.openanimation.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.osg.openanimation.core.data.stats.AnimationStats
import com.osg.openanimation.core.ui.color.model.ColorsEditHandler
import com.osg.openanimation.core.ui.color.model.ColorsEditPalette
import com.osg.openanimation.core.ui.components.lottie.AnimationDataState
import com.osg.openanimation.core.ui.details.AnimationDetailsPanes
import com.osg.openanimation.core.ui.details.model.ds.DetailsScreenStates
import com.osg.openanimation.core.ui.details.model.ds.DetailsUiPane
import com.osg.openanimation.core.ui.home.domain.ColorPaletteWithMetadata
import com.osg.openanimation.core.ui.theme.TrueTheme
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun AnimationDetailsPanesAndroidShimmerPreview() {
    var isClicked by remember { mutableStateOf(false) }
    var isLike by remember { mutableStateOf(true) }

    TrueTheme{
        val a = generateAnimationUiDataList().last()
        val detailsScreenState = DetailsScreenStates.Success(
            detailsUiPane = DetailsUiPane(
                isLiked = isLike,
                animationUiData = ColorPaletteWithMetadata(
                    metadata = a.metadata,
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
                ),
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
            onPalletSelect = {},
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
        val a = generateAnimationUiDataList().last()
        val handler = ColorsEditHandler(
            path = a.metadata.localFileName,
            animationContentLoader = { hash ->
                val f = a.animationState as AnimationDataState.LazyLoading
                f.lazyLoader()
            },
            scope = rememberCoroutineScope()
        )
        val s by handler.uiState.collectAsState()
        val detailsScreenState = DetailsScreenStates.Success(
            detailsUiPane = DetailsUiPane(
                animationUiData = ColorPaletteWithMetadata(
                    metadata = a.metadata,
                    editableAnimation = s.copy(
                        processedJsonState = AnimationDataState.Processing,
                        options = s.options + s.options,
                    ),
                ),
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
            onPalletSelect = { index ->

            },
        )
    }
}