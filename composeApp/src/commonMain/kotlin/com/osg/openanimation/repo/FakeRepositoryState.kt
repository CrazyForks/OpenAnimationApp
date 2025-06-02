package com.osg.openanimation.repo

import com.osg.openanimation.core.data.stats.AnimationStats
import com.osg.openanimation.repo.AnimationDataCollection.*
import kotlinx.coroutines.flow.MutableStateFlow

const val TEST_USER_UID_PREFIX = "testUserUidPrefix"

object FakeRepositoryState{
    val uidState = MutableStateFlow<String?>(null)
    val statsState: MutableStateFlow<Map<String, AnimationStats>> = MutableStateFlow(entries.associate {
        it.metadata.hash to it.initialStats
    })

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
    }