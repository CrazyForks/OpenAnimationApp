package com.osg.openanimation.repo

import com.osg.openanimation.core.ui.di.AnimationContentLoader
import kotlinx.coroutines.delay
import openanimationapp.composeapp.generated.resources.Res
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class FakeAnimationContentLoader(
    private val networkSimulateDelay: Duration = 500.milliseconds,
): AnimationContentLoader {
    override suspend fun fetchAnimationByPath(path: String): ByteArray {
        delay(networkSimulateDelay)
        return Res.readBytes("files/$path")
    }
}