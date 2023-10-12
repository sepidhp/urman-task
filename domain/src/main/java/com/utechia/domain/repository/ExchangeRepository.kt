package com.utechia.domain.repository

import com.utechia.domain.model.ExchangeName
import com.utechia.domain.util.Result

interface ExchangeRepository {
    suspend fun getExchangeNames(): Result<List<ExchangeName>>
}