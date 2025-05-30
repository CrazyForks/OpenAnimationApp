package com.osg.openanimation.repo

import com.osg.core.di.data.FilterQueryType
import com.osg.core.di.data.GuestQueryType
import com.osg.core.di.data.SelectedQueryType
import com.osg.openanimation.core.data.animation.AnimationMetadata
import com.osg.openanimation.core.data.stats.AnimationStats
import com.osg.openanimation.core.ui.di.AnimationMetadataRepository
import com.osg.openanimation.core.ui.home.model.extractSortedTags
import com.osg.openanimation.core.ui.home.model.filterSortByText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AnimationMetadataRepositoryFake : AnimationMetadataRepository {
    private fun fetchTradingAnimationIds(): Set<String> {
        val statsMap = RepositoryFakeStateFlow.statsState.value
        return statsMap.entries.sortedByDescending {it.value.likeCount + it.value.downloadCount }
            .map { it.key }
            .toSet()
    }

    override suspend fun fetchMetaByHash(hash: String): AnimationMetadata {
        return AnimationDataCollection.entries.first {
            it.metadata.hash == hash
        }.metadata
    }

    override suspend fun fetchRelatedAnimations(
        animationMetadata: AnimationMetadata,
        count: Int
    ): List<AnimationMetadata> {
        return AnimationDataCollection.metaList
            .sortedByDescending {
                it.tags.intersect(animationMetadata.tags).size
            }.take(4)
    }

    override fun animationStatsFlow(hash: String): Flow<AnimationStats> = RepositoryFakeStateFlow.statsState.map {
        it[hash] ?: AnimationStats()
    }

    override suspend fun fetchBy(
        queryType: GuestQueryType,
        limit: Int
    ): List<AnimationMetadata> {
        return when(queryType){
            is FilterQueryType -> {
                AnimationDataCollection.entries
                    .map { it.metadata }
                    .filterSortByText(queryType)
            }
            is SelectedQueryType.ExploreCategory.Explore ->{
                AnimationDataCollection.metaList
            }

            SelectedQueryType.ExploreCategory.Trending -> {
                val trendingIds = fetchTradingAnimationIds()
                return trendingIds.map {
                    AnimationDataCollection.byHash(it).metadata
                }
            }
        }

    }

    override suspend fun fetchTags(): List<String> {
        return AnimationDataCollection.entries.map { it.metadata }.extractSortedTags()
    }
}

