package com.osg.openanimation.repo

import com.osg.openanimation.core.data.stats.AnimationStats
import com.osg.openanimation.core.data.upload.UploadedAnimationMeta
import com.osg.openanimation.core.data.user.UserProfile
import com.osg.openanimation.core.ui.components.signin.SignInResult
import com.osg.openanimation.core.ui.di.domain.UserRepository
import com.osg.openanimation.core.ui.di.domain.UserSessionState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import org.koin.core.annotation.Factory
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Factory
class FakeUserRepo(): UserRepository {
    private val networkSimulateDelay: Duration = 300.milliseconds


    @OptIn(ExperimentalCoroutinesApi::class)
    override val profileFlow: Flow<UserSessionState> = FakeRepositoryState.profileState.flatMapLatest {
        FakeRepositoryState.userLikedAnimationsState.map { likedSet ->
            if (it == null) {
                UserSessionState.SignedOut
            } else {
                UserSessionState.SignedIn(
                    userProfile = it,
                    favorites = likedSet
                )
            }
        }
    }


    override suspend fun onUserDownload(hash: String) {
        delay(networkSimulateDelay)
        FakeRepositoryState.statsState.update {
            it.toMutableMap().apply {
                val currentStats = this[hash]?: AnimationStats()
                this[hash] = currentStats.copy(
                    downloadCount = currentStats.downloadCount + 1
                )
            }
        }
    }

    override suspend fun likeAnimation(hash: String) {
        delay(networkSimulateDelay)
        FakeRepositoryState.userLikedAnimationsState.update { likedSet ->
            likedSet + hash
        }

        FakeRepositoryState.statsState.update {
            it.toMutableMap().apply {
                val currentStats = this[hash]?: AnimationStats()
                this[hash] = currentStats.copy(
                    likeCount = currentStats.likeCount + 1
                )
            }
        }
    }

    override suspend fun dislikeAnimation(hash: String) {
        delay(networkSimulateDelay)
        FakeRepositoryState.userLikedAnimationsState.update { likedSet ->
            likedSet - hash
        }

        FakeRepositoryState.statsState.update {
            it.toMutableMap().apply {
                val currentStats = this[hash]?: AnimationStats()
                this[hash] = currentStats.copy(
                    likeCount = currentStats.likeCount - 1
                )
            }
        }
    }

    override fun userAnimationsFlow(uid: String): Flow<List<UploadedAnimationMeta>> {
        return FakeRepositoryState.uploadedAnimationsMeta.map { it.values.toList() }
    }

    override fun onUserSignOut() {
        FakeRepositoryState.profileState.value = null
    }

    override fun onRegistered(signInResultState: Result<SignInResult>) {

    }

    override suspend fun updateProfile(userProfile: UserProfile) {
        FakeRepositoryState.profileState.value = userProfile
    }
}