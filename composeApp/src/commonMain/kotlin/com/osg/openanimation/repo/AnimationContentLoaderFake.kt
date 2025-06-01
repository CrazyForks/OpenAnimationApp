package com.osg.openanimation.repo

import com.osg.openanimation.core.ui.di.AnimationContentLoader
import kotlinx.coroutines.delay
import openanimationapp.composeapp.generated.resources.Res
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class AnimationContentLoaderFake(
    private val networkSimulateDelay: Duration = 1.seconds,
): AnimationContentLoader {
    override suspend fun fetchAnimationByPath(path: String): ByteArray {
        delay(networkSimulateDelay)
        return Res.readBytes("files/$path")
    }
}