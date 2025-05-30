package com.osg.openanimation.repo

import com.osg.core.ui.di.AnimationDataFetcher
import openanimationapp.composeapp.generated.resources.Res
class AnimationDataFetcherFake: AnimationDataFetcher {
    override suspend fun fetchAnimationByPath(path: String): ByteArray {
        return Res.readBytes("files/$path")
    }
}