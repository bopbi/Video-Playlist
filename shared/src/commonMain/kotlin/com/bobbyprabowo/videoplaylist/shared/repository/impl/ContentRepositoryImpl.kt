package com.bobbyprabowo.videoplaylist.shared.repository.impl

import com.bobbyprabowo.videoplaylist.db.VideoPlaylistDatabase
import com.bobbyprabowo.videoplaylist.shared.datasource.remote.VideoApi
import com.bobbyprabowo.videoplaylist.shared.repository.ContentRepository
import com.bobbyprabowo.videoplaylist.shared.schema.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ContentRepositoryImpl(
    private val videoApi: VideoApi,
    private val database: VideoPlaylistDatabase
) : ContentRepository {
    override fun getPlaylist(): Flow<List<Content>> {
        return flowOf(Content.fromEntities(database.contentQueries.getContents().executeAsList()))
    }

    override fun fetchPlaylist(): Flow<Unit> {
        return videoApi.fetchPlaylist().map {contentList ->
            database.contentQueries.deleteAllContents()
            contentList.forEach { content ->
                database.contentQueries.insertContent(content.toEntity())
            }

        }
    }
}