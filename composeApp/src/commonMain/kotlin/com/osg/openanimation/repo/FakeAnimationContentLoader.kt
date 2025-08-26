package com.osg.openanimation.repo

import com.osg.openanimation.core.ui.di.domain.AnimationContentLoader
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import openanimationapp.composeapp.generated.resources.Res
import org.koin.core.annotation.Factory
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds


@Factory
class FakeAnimationContentLoader() : AnimationContentLoader {
    private val networkSimulateDelay: Duration = 500.milliseconds
    override suspend fun fetchAnimationByPath(path: String): String {
        delay(networkSimulateDelay)

        return Res.readBytes("files/$path").decodeToString()
    }
}

object FakeAnimationStorage {
    private val storage = MutableStateFlow<Map<String, String>>(emptyMap())
    fun storeAnimation(path: String, data: String) {
        storage.value = storage.value + (path to data)
    }

    fun deleteAnimation(path: String) {
        storage.value = storage.value - path
    }

    fun getAnimation(path: String): String? {
        return storage.value[path]
    }
}