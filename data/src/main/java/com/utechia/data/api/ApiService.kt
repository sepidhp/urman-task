package com.utechia.data.api

import com.utechia.data.model.exchange.ExchangeResponseRemote
import com.utechia.data.model.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("auth/authentication/guestToken")
    suspend fun fetchGuestToken(
        @Header("userType") userType: String,
        @Header("uniqueId") uniqueId: String
    ): ResponseWrapper<String>

    @GET("sideservices/exchange/initializeResources")
    suspend fun fetchExchanges(): ResponseWrapper<ExchangeResponseRemote>
}