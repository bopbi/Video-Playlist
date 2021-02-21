package com.bobbyprabowo.videoplaylist.shared.usecase.listener

import com.bobbyprabowo.videoplaylist.shared.schema.Content

interface FetchPlaylistResultListener {

    fun onSuccess(playlist: List<Content>)

    fun onFailed(throwable: Throwable)
}
