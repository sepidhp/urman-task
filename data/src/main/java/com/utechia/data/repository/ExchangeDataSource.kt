package com.utechia.data.repository

import com.utechia.domain.model.Exchange
import com.utechia.domain.util.Result

interface ExchangeDataSource {
    suspend fun getExchanges(): Result<List<Exchange>>
}