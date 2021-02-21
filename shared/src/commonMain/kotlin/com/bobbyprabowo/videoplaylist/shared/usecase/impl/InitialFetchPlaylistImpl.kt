package com.bobbyprabowo.videoplaylist.shared.usecase.impl

import com.bobbyprabowo.videoplaylist.shared.repository.ContentRepository
import com.bobbyprabowo.videoplaylist.shared.schema.Content
import com.bobbyprabowo.videoplaylist.shared.usecase.InitialFetchPlaylist
import com.bobbyprabowo.videoplaylist.shared.usecase.listener.FetchPlaylistResultListener
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*

class InitialFetchPlaylistImpl(private val contentRepository: ContentRepository) : InitialFetchPlaylist {
    @FlowPreview
    override fun execute(): Flow<List<Content>> {
        return contentRepository.fetchPlaylist().flatMapConcat {
            contentRepository.getPlaylist()
        }
    }

    @FlowPreview
    override fun execute(resultListener: FetchPlaylistResultListener) {
        contentRepository
            .fetchPlaylist()
            .flatMapConcat {
                contentRepository.getPlaylist()
            }
            .onEach { playlist ->
                resultListener.onSuccess(playlist)
            }
            .catch {
                resultListener.onFailed(it)
            }
            .launchIn(MainScope())
    }
}