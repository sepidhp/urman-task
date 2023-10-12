package com.utechia.data.repository

import com.utechia.domain.model.Exchange
import com.utechia.domain.repository.ExchangeRepository
import com.utechia.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRepositoryImpl @Inject constructor(private val dataSource: ExchangeDataSource) :
    ExchangeRepository {
    override suspend fun getExchanges(): Result<List<Exchange>> = dataSource.getExchanges()
}