package com.osg.openanimation.repo

import com.osg.core.ui.di.AnimationContentLoader
import openanimationapp.composeapp.generated.resources.Res
class AnimationContentLoaderFake: AnimationContentLoader {
    override suspend fun fetchAnimationByPath(path: String): ByteArray {
        return Res.readBytes("files/$path")
    }
}