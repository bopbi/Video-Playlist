package com.bobbyprabowo.videoplaylist.shared.repository

import com.bobbyprabowo.videoplaylist.shared.schema.Content
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    fun getPlaylist(): Flow<List<Content>>

    fun fetchPlaylist(): Flow<Unit>
}
