package com.utechia.data.model

data class ResponseWrapper<out T>(
    val devMessage: String,
    val clientMessage: String,
    val data: T
)