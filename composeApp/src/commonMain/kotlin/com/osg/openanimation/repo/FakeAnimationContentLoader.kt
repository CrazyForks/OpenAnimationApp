package com.osg.openanimation.repo

import com.osg.openanimation.core.ui.di.domain.AnimationContentLoader
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import openanimationapp.composeapp.generated.resources.Res
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class FakeAnimationContentLoader(
    private val networkSimulateDelay: Duration = 500.milliseconds,
) : AnimationContentLoader {
    override suspend fun fetchAnimationByPath(path: String): String {
        delay(networkSimulateDelay)

        return FakeAnimationStorage.getAnimation(path) ?: run {
            Res.readBytes("files/$path").decodeToString()
        }
    }
}

object FakeAnimationStorage {
    private val storage = MutableStateFlow<Map<String, String>>(emptyMap())
    fun storeAnimation(animationId: String, data: String) {
        storage.value = storage.value + (animationId to data)
    }

    fun getAnimation(path: String): String? {
        return storage.value[path]
    }
}