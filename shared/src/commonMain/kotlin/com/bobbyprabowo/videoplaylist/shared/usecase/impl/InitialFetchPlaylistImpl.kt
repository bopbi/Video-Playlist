package com.bobbyprabowo.videoplaylist.shared.usecase.impl

import com.bobbyprabowo.videoplaylist.shared.repository.ContentRepository
import com.bobbyprabowo.videoplaylist.shared.schema.Content
import com.bobbyprabowo.videoplaylist.shared.usecase.InitialFetchPlaylist
import com.bobbyprabowo.videoplaylist.shared.usecase.UseCaseResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class InitialFetchPlaylistImpl(
    private val contentRepository: ContentRepository
) : InitialFetchPlaylist {
    @FlowPreview
    override fun execute(): Flow<UseCaseResult<List<Content>>> {
        return contentRepository.fetchPlaylist().flatMapConcat {
            contentRepository.getPlaylist()
        }.map { playlist ->
            UseCaseResult.Success(playlist)
        }.catch { error ->
            UseCaseResult.Fail<List<Content>>(error)
        }
    }
}