package com.utechia.data.repository

import com.utechia.domain.model.ExchangeName
import com.utechia.domain.util.Result

interface ExchangeDataSource {
    suspend fun getExchangeNames(): Result<List<ExchangeName>>
}