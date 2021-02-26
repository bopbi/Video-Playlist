package com.bobbyprabowo.videoplaylist.shared.usecase.impl

import com.bobbyprabowo.videoplaylist.shared.repository.ContentRepository
import com.bobbyprabowo.videoplaylist.shared.schema.Content
import com.bobbyprabowo.videoplaylist.shared.usecase.InitialLoadPlaylist
import com.bobbyprabowo.videoplaylist.shared.usecase.UseCaseResult
import kotlinx.coroutines.flow.*

class InitialLoadPlaylistImpl(
    private val repository: ContentRepository
) : InitialLoadPlaylist {
    override fun execute(): Flow<UseCaseResult<List<Content>>> {
        return repository.getPlaylist().map { playlist ->
            UseCaseResult.Success(playlist)
        }.catch { error ->
            UseCaseResult.Fail<List<Content>>(error)
        }
    }
}