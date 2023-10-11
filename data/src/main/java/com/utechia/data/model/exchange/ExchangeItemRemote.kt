package com.utechia.data.model.exchange

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangeItemRemote(
    val value: String?,
    val sortId: Int?
) : Parcelable
