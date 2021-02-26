package com.bobbyprabowo.videoplaylist.shared.usecase

import com.bobbyprabowo.videoplaylist.shared.schema.Content
import kotlinx.coroutines.flow.Flow

interface InitialFetchPlaylist {

    fun execute(): Flow<UseCaseResult<List<Content>>>
}