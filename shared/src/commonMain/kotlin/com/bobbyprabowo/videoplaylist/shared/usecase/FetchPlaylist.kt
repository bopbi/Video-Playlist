package com.bobbyprabowo.videoplaylist.shared.usecase

import kotlinx.coroutines.flow.Flow

interface FetchPlaylist {

    fun execute(): Flow<Unit>
}