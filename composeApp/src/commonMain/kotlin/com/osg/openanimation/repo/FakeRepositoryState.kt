package com.osg.openanimation.repo

import com.osg.openanimation.core.data.stats.AnimationStats
import com.osg.openanimation.core.data.upload.ModerationStatus
import com.osg.openanimation.core.data.upload.UploadedAnimationMeta
import com.osg.openanimation.core.data.user.UserProfile
import com.osg.openanimation.repo.AnimationDataCollection.AIRPLANE
import com.osg.openanimation.repo.AnimationDataCollection.CHECKMARK
import com.osg.openanimation.repo.AnimationDataCollection.DAY_NIGHT_CYCLE
import com.osg.openanimation.repo.AnimationDataCollection.HAPPY_DOG
import com.osg.openanimation.repo.AnimationDataCollection.ORBITING_DOTS
import com.osg.openanimation.repo.AnimationDataCollection.PARTLY_CLOUDY_SUN
import com.osg.openanimation.repo.AnimationDataCollection.SIMPLE_JUMP
import com.osg.openanimation.repo.AnimationDataCollection.SURPRISED_FACE
import com.osg.openanimation.repo.AnimationDataCollection.SYNC_DOTS
import com.osg.openanimation.repo.AnimationDataCollection.WALKING_DOG
import com.osg.openanimation.repo.AnimationDataCollection.entries
import kotlinx.coroutines.flow.MutableStateFlow


object FakeRepositoryState{

    val profileState = MutableStateFlow<UserProfile?>(null)
    val statsState: MutableStateFlow<Map<String, AnimationStats>> = MutableStateFlow(entries.associate {
        it.metadata.hash to it.initialStats
    })

    val uploadedAnimationsMeta = MutableStateFlow<Map<String, UploadedAnimationMeta>>(emptyMap())

    val moderationStatusState: MutableStateFlow<Map<String, ModerationStatus>> = MutableStateFlow(emptyMap())

    val userLikedAnimationsState: MutableStateFlow<Set<String>> = MutableStateFlow(emptySet())
}


private val AnimationDataCollection.initialStats: AnimationStats
    get() = when (this) {
        CHECKMARK -> AnimationStats(
            downloadCount = 1000,
            likeCount = 500
        )
        AIRPLANE -> AnimationStats(
            downloadCount = 2000,
            likeCount = 1500
        )
        SIMPLE_JUMP -> AnimationStats(
            downloadCount = 750,
            likeCount = 320
        )
        DAY_NIGHT_CYCLE -> AnimationStats(
            downloadCount = 1200,
            likeCount = 600
        )
        SURPRISED_FACE -> AnimationStats(
            downloadCount = 400,
            likeCount = 180
        )
        HAPPY_DOG -> AnimationStats(
            downloadCount = 2100,
            likeCount = 1700
        )
        ORBITING_DOTS -> AnimationStats(
            downloadCount = 950,
            likeCount = 410
        )
        WALKING_DOG -> AnimationStats(
            downloadCount = 1300,
            likeCount = 900
        )
        PARTLY_CLOUDY_SUN -> AnimationStats(
            downloadCount = 500,
            likeCount = 250
        )
        SYNC_DOTS -> AnimationStats(
            downloadCount = 1600,
            likeCount = 1200
        )

        AnimationDataCollection.LOADING_EMOJI -> AnimationStats(
            downloadCount = 700,
            likeCount = 355
        )
    }