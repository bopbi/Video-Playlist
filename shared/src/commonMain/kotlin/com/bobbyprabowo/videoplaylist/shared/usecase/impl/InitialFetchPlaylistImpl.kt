package com.bobbyprabowo.videoplaylist.shared.usecase.impl

import com.bobbyprabowo.videoplaylist.shared.repository.ContentRepository
import com.bobbyprabowo.videoplaylist.shared.schema.Content
import com.bobbyprabowo.videoplaylist.shared.usecase.InitialFetchPlaylist
import com.bobbyprabowo.videoplaylist.shared.usecase.listener.UseCaseFailureListener
import com.bobbyprabowo.videoplaylist.shared.usecase.listener.UseCaseSuccessListener
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class InitialFetchPlaylistImpl(
    private val contentRepository: ContentRepository,
    private val successListener: UseCaseSuccessListener<List<Content>>?,
    private val failureListener: UseCaseFailureListener?
) : InitialFetchPlaylist {
    @FlowPreview
    override fun execute(): Flow<List<Content>> {
        return contentRepository.fetchPlaylist().flatMapConcat {
            contentRepository.getPlaylist()
        }
    }

    @FlowPreview
    override fun executeCallback(processTag: String) {
        execute()
            .onEach { result ->
                successListener?.onSuccess(processTag, result)
            }
            .catch { throwable ->
                failureListener?.onError(processTag, throwable)
            }
            .launchIn(CoroutineScope(SupervisorJob() + Dispatchers.Main))
    }
}