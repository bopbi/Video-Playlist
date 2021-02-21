package com.bobbyprabowo.videoplaylist.shared.usecase

import com.bobbyprabowo.videoplaylist.shared.schema.Content
import com.bobbyprabowo.videoplaylist.shared.usecase.listener.FetchPlaylistResultListener
import kotlinx.coroutines.flow.Flow

interface InitialFetchPlaylist {

    fun execute(): Flow<List<Content>>

    fun execute(resultListener: FetchPlaylistResultListener)
}