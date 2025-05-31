package com.osg.openanimation.repo

import com.osg.openanimation.core.ui.di.UserSessionState
import com.osg.openanimation.core.ui.di.UserRepository
import com.osg.openanimation.core.data.stats.AnimationStats
import com.osg.openanimation.core.data.use.UserProfile
import com.osg.openanimation.core.ui.components.signin.SignInResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class UserRepositoryFake: UserRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val profileFlow: Flow<UserSessionState> = RepositoryFakeStateFlow.uidState.flatMapLatest {
        RepositoryFakeStateFlow.userLikedAnimationsState.map { likedSet ->
            if (it == null) {
                UserSessionState.SignedOut
            } else {
                UserSessionState.SignedIn(
                    userProfile = UserProfile(
                        uid = it,
                        firstName = "Test User",
                        email = "test@gamil.com"
                    ),
                    favorites = likedSet
                )
            }
        }
    }


    override suspend fun onUserDownload(hash: String) {
        RepositoryFakeStateFlow.statsState.update {
            it.toMutableMap().apply {
                val currentStats = this[hash]?: AnimationStats()
                this[hash] = currentStats.copy(
                    downloadCount = currentStats.downloadCount + 1
                )
            }
        }
    }

    override suspend fun likeAnimation(hash: String) {
        RepositoryFakeStateFlow.userLikedAnimationsState.update { likedSet ->
            likedSet + hash
        }

        RepositoryFakeStateFlow.statsState.update {
            it.toMutableMap().apply {
                val currentStats = this[hash]?: AnimationStats()
                this[hash] = currentStats.copy(
                    likeCount = currentStats.likeCount + 1
                )
            }
        }
    }

    override suspend fun dislikeAnimation(hash: String) {
        RepositoryFakeStateFlow.userLikedAnimationsState.update { likedSet ->
            likedSet - hash
        }

        RepositoryFakeStateFlow.statsState.update {
            it.toMutableMap().apply {
                val currentStats = this[hash]?: AnimationStats()
                this[hash] = currentStats.copy(
                    likeCount = currentStats.likeCount - 1
                )
            }
        }
    }

    override fun onUserSignOut() {
        RepositoryFakeStateFlow.uidState.value = null
    }

    override fun onRegistered(signInResultState: Result<SignInResult>) {

    }
}