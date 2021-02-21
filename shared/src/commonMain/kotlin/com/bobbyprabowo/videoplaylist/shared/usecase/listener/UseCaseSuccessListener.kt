package com.bobbyprabowo.videoplaylist.shared.usecase.listener

interface UseCaseSuccessListener<T> {

    fun onSuccess(tag: String, result: T)
}