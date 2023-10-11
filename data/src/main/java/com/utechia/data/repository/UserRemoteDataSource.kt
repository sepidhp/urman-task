package com.utechia.data.repository

import com.google.gson.Gson
import com.utechia.data.api.ApiService
import com.utechia.data.exceptions.NetworkExceptionHandler
import com.utechia.domain.model.*
import javax.inject.Inject
import javax.inject.Singleton
import com.utechia.domain.util.Result

@Singleton
class UserRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val apiExceptionHandler: NetworkExceptionHandler,
    private val gson: Gson
) : UserDataSource {
    override suspend fun getGuestToken(userType: String, uniqueId: String): Result<String> {
        return try {
            val result = apiService.fetchGuestToken(userType, uniqueId)
            Result.Success(result.data)
        } catch (e: java.lang.Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun getExchanges(): Result<List<Exchange>> {
        return try {
            val result = apiService.fetchExchanges()
            Result.Success(result.data.translates.en.toDomain())
        } catch (e: java.lang.Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }
}