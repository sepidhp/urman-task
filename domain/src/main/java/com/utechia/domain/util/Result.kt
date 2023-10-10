package com.utechia.domain.util

import com.utechia.domain.model.ErrorMessage

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: ErrorMessage) : Result<T>()
    data class Loading<T>(val loading: Boolean) : Result<T>()
}