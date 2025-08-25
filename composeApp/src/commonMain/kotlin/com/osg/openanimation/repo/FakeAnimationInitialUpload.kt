package com.osg.openanimation.repo

import com.osg.openanimation.core.data.upload.ModerationStatus
import com.osg.openanimation.core.data.upload.UploadedAnimationMeta
import com.osg.openanimation.core.ui.di.domain.AnimationUploader
import com.osg.openanimation.core.ui.di.domain.UploadedMetadataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import org.koin.core.annotation.Factory
import kotlin.time.Clock

@Factory
class FakeAnimationInitialUpload: AnimationUploader, UploadedMetadataRepository {
    override suspend fun processUploadAnimation(animationContent: String): String {
        val timeStamp = Clock.System.now().toEpochMilliseconds()
        val animationId = "uid_$timeStamp"
        val derivedMetaData = UploadedAnimationMeta(
            hash = animationId,
            uploadTimestamp = timeStamp,
            name = "My Animation Name",
            description = "My Animation Description",
            path = "$animationId.json",
            tags = emptyList(),
            isSubmitted = false,
        )
        uploadAnimation(derivedMetaData, animationContent)

        return animationId
    }

    override suspend fun uploadAnimation(
        uploadedAnimationMeta: UploadedAnimationMeta,
        animationContent: String?
    ) {
        if (animationContent != null) {
            FakeAnimationStorage.storeAnimation(uploadedAnimationMeta.path, animationContent)
        }
        FakeRepositoryState.uploadedAnimationsMeta.update { currentMap ->
            currentMap + (uploadedAnimationMeta.hash to uploadedAnimationMeta)
        }

        if (uploadedAnimationMeta.isSubmitted) {
            FakeRepositoryState.moderationStatusState.update { currentMap ->
                currentMap + (uploadedAnimationMeta.hash to ModerationStatus.PENDING)
            }
        }
    }

    override fun moderationStatusFlow(hash: String): Flow<ModerationStatus> {
        return FakeRepositoryState.moderationStatusState.map { currentMap ->
            currentMap[hash] ?: ModerationStatus.DRAFT
        }
    }

    override fun uploadedMetaFlow(hash: String): Flow<UploadedAnimationMeta> {
        return FakeRepositoryState.uploadedAnimationsMeta.map { currentMap ->
            currentMap.getValue(hash)
        }
    }

    override suspend fun onRemoveAnimation(hash: String) {
        val meta = FakeRepositoryState.uploadedAnimationsMeta.value.getValue(hash)
        FakeAnimationStorage.deleteAnimation(meta.path)
        FakeRepositoryState.uploadedAnimationsMeta.update { currentMap ->
            currentMap - hash
        }
    }
}