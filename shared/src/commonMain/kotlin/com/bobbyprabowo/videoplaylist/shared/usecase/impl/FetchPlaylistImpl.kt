package com.bobbyprabowo.videoplaylist.shared.usecase.impl

import com.bobbyprabowo.videoplaylist.shared.repository.ContentRepository
import com.bobbyprabowo.videoplaylist.shared.usecase.FetchPlaylist
import kotlinx.coroutines.flow.Flow

class FetchPlaylistImpl(private val contentRepository: ContentRepository) : FetchPlaylist {
    override fun execute(): Flow<Unit> {
        return contentRepository.fetchPlaylist()
    }
}