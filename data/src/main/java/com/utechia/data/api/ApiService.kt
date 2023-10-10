package com.utechia.data.api

import com.utechia.data.model.ExchangeRemote
import retrofit2.http.GET

interface ApiService {
    @GET("sideservices/exchange/initializeResources")
    suspend fun fetchExchanges(): ExchangeRemote
}
