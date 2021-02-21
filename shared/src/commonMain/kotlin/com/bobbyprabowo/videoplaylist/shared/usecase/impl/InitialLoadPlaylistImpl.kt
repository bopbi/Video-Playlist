package com.bobbyprabowo.videoplaylist.shared.usecase.impl

import com.bobbyprabowo.videoplaylist.shared.repository.ContentRepository
import com.bobbyprabowo.videoplaylist.shared.schema.Content
import com.bobbyprabowo.videoplaylist.shared.usecase.InitialLoadPlaylist
import com.bobbyprabowo.videoplaylist.shared.usecase.listener.UseCaseFailureListener
import com.bobbyprabowo.videoplaylist.shared.usecase.listener.UseCaseSuccessListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class InitialLoadPlaylistImpl(
    private val repository: ContentRepository,
    private val successListener: UseCaseSuccessListener<List<Content>>? = null,
    private val failureListener: UseCaseFailureListener? = null
) : InitialLoadPlaylist {
    override fun execute(): Flow<List<Content>> {
        return repository.getPlaylist()
    }

    override fun executeCallback(requestTag: String) {
        execute()
            .onEach { contents ->
                successListener?.onSuccess(requestTag, contents)
            }
            .catch { throwable ->
                failureListener?.onError(requestTag, throwable)
            }
            .launchIn(CoroutineScope(SupervisorJob() + Dispatchers.Main))
    }
}