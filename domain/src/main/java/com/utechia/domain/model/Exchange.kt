package com.utechia.domain.model

data class Exchange(
    val value: String?,
    val sortId: Int?,
    val name: String?,
    var buyPrice: Double?,
    var sellPrice: Double?
)
