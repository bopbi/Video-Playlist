package com.bobbyprabowo.videoplaylist.shared.repository.impl

import com.bobbyprabowo.videoplaylist.shared.datasource.remote.VideoApi
import com.bobbyprabowo.videoplaylist.shared.repository.ContentRepository
import com.bobbyprabowo.videoplaylist.shared.schema.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContentRepositoryImpl(private val videoApi: VideoApi) : ContentRepository {
    override fun getPlaylist(): Flow<List<Content>> {
        TODO("Not yet implemented")
    }

    override fun fetchPlaylist(): Flow<Unit> {
        return videoApi.fetchPlaylist().map {
            println(it)
            Unit
        }
    }
}