package com.utechia.domain.model

import com.utechia.domain.util.HttpErrors

data class ErrorMessage(
    val message: String?,
    val status: HttpErrors,
)