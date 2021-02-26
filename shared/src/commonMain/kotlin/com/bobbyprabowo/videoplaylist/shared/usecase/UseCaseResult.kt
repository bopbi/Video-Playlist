package com.bobbyprabowo.videoplaylist.shared.usecase

data class UseCaseResult<T>(val value: T?, val error: Throwable?) {

    companion object {

        fun <T> Success(value: T): UseCaseResult<T> {
            return UseCaseResult(value, null)
        }

        fun <T> Fail(throwable: Throwable): UseCaseResult<T> {
            return UseCaseResult(null, throwable)
        }
    }

    fun isSuccess(): Boolean {
        return value != null
    }

    fun isFailed(): Boolean {
        return error != null
    }
}
