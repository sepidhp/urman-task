package com.utechia.domain.usecase

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.utechia.domain.model.Exchange
import com.utechia.domain.model.Price
import com.utechia.domain.model.PriceResponse
import javax.inject.Inject
import javax.inject.Singleton
import com.utechia.domain.util.Result
import org.json.JSONObject

@Singleton
class PriceMapperUseCase @Inject constructor(
    private val gson: Gson
) {

    operator fun invoke(data: Any): PriceResponse {
        return mapData(data)
    }

    private fun mapData(data: Any): PriceResponse {
        return gson.fromJson((data as JSONObject).toString(), PriceResponse::class.java)
    }
}