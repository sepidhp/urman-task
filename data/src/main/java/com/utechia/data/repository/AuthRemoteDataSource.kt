package com.utechia.data.repository

import com.utechia.data.api.ApiService
import com.utechia.data.exceptions.NetworkExceptionHandler
import com.utechia.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val apiExceptionHandler: NetworkExceptionHandler
) : AuthDataSource {
    override suspend fun getGuestToken(userType: String, uniqueId: String): Result<String> {
        return try {
            val result = apiService.fetchGuestToken(userType, uniqueId)
            Result.Success(result.data)
        } catch (e: Exception) {
            Result.Error(apiExceptionHandler.traceErrorException(e))
        }
    }
}