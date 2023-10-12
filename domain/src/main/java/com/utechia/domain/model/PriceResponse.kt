package com.utechia.domain.model

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("exchange_prices")
    val exchangePrices: ExchangePrice
)
