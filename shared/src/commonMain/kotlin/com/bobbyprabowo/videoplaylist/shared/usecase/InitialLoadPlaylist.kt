package com.bobbyprabowo.videoplaylist.shared.usecase

import com.bobbyprabowo.videoplaylist.shared.schema.Content
import kotlinx.coroutines.flow.Flow

interface InitialLoadPlaylist {

    fun execute(): Flow<UseCaseResult<List<Content>>>
}