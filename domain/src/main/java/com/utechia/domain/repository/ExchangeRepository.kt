package com.utechia.domain.repository

import com.utechia.domain.model.Exchange
import com.utechia.domain.util.Result

interface ExchangeRepository {
    suspend fun getExchanges(): Result<List<Exchange>>
}