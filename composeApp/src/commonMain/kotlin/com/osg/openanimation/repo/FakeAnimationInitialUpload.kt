package com.osg.openanimation.repo

import com.osg.openanimation.core.data.upload.UploadedAnimationMeta
import com.osg.openanimation.core.ui.di.domain.AnimationInitialUpload
import kotlinx.coroutines.flow.update
import kotlin.time.Clock

class FakeAnimationInitialUpload: AnimationInitialUpload {
    override suspend fun processUploadAnimation(animationContent: String): String {
        val timeStamp = Clock.System.now().toEpochMilliseconds()
        val animationId = "uid_$timeStamp"
        val derivedMetaData = UploadedAnimationMeta(
            animationId = animationId,
            uploadTimestamp = timeStamp,
            name = "My Animation Name",
            description = "My Animation Description",
            path = "$animationId.json",
            tags = emptyList()
        )
        FakeAnimationStorage.storeAnimation(animationId, animationContent)
        FakeRepositoryState.uploadedAnimationsMeta.update { currentMap ->
            currentMap + (animationId to derivedMetaData)
        }

        return animationId
    }
}