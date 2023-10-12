package com.utechia.data.repository

import com.utechia.data.api.ApiService
import com.utechia.data.exceptions.NetworkExceptionHandler
import com.utechia.domain.model.ExchangeName
import com.utechia.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val apiExceptionHandler: NetworkExceptionHandler
) : ExchangeDataSource {

    override suspend fun getExchangeNames(): Result<List<ExchangeName>> {
        return try {
            val result = apiService.fetchExchanges()
            Result.Success(result.data.translates.en.toDomain())
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }
}