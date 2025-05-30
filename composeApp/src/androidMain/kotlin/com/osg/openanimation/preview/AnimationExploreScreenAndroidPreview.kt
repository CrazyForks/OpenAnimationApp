package com.osg.openanimation.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.osg.core.di.data.SelectedQueryType
import com.osg.openanimation.core.ui.home.domain.AnimationUiData
import com.osg.openanimation.core.ui.home.domain.ExploreScreenStates
import com.osg.openanimation.core.ui.home.ui.AnimationGrid
import com.osg.openanimation.core.ui.home.ui.strResource
import com.osg.openanimation.core.ui.util.resource.string
import com.osg.openanimation.core.ui.theme.TrueTheme
import com.osg.openanimation.repo.AnimationDataCollection
import com.osg.openanimation.core.data.animation.AnimationCategory
import com.osg.openanimation.repo.fromLocaleStorage


fun generateAnimationUiDataList(): List<AnimationUiData> {
    return AnimationDataCollection.entries.take(20).map {
        AnimationUiData(
            animationState = it.fromLocaleStorage(),
            metadata = it.metadata,
        )
    }
}

@PreviewScreenSizes
@Preview
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
