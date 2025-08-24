package com.osg.openanimation.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.osg.openanimation.core.data.animation.AnimationCategory
import com.osg.openanimation.core.ui.components.lottie.AnimationDataState
import com.osg.openanimation.core.ui.graph.SelectedQueryType
import com.osg.openanimation.core.ui.home.domain.AnimationUiData
import com.osg.openanimation.core.ui.home.domain.ExploreScreenStates
import com.osg.openanimation.core.ui.home.ui.AnimationGrid
import com.osg.openanimation.core.ui.home.ui.strResource
import com.osg.openanimation.core.ui.theme.TrueTheme
import com.osg.openanimation.core.ui.util.resource.string
import com.osg.openanimation.repo.AnimationDataCollection
import com.osg.openanimation.repo.fromLocaleStorage


@Composable
fun generateAnimationUiDataList(): List<AnimationUiData> {
    val animations = AnimationDataCollection.entries.take(20)
    var animationState by remember { mutableStateOf(
        animations.map {
            AnimationDataState.LazyLoading(it.metadata.hash){
                it.fromLocaleStorage()
            }
        }
    ) }

    return animations.zip(animationState) { it, state ->
        AnimationUiData(
            animationState = state,
            metadata = it.metadata,
        )
    }
}

@PreviewLightDark
@PreviewScreenSizes
@Composable
fun AnimationExploreScreenAndroidPreview() {
    TrueTheme {
        AnimationGrid(
            screenData = ExploreScreenStates.Success(
                animations = generateAnimationUiDataList(),
                categories = AnimationCategory.entries.map {
                    SelectedQueryType.Tag(it.strResource.string)
                },
                selected = SelectedQueryType.ExploreCategory.Explore
            ),
            onDestination = {},
        )
    }
}
