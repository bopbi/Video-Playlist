package com.bobbyprabowo.videoplaylist.shared.usecase.listener

interface UseCaseFailureListener {
    fun onError(tag: String, throwable: Throwable)
}