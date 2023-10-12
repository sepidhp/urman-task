package com.utechia.data.repository

import com.utechia.domain.repository.AuthRepository
import com.utechia.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthDataSource) :
    AuthRepository {
    override suspend fun getGuestToken(userType: String, uniqueId: String): Result<String> =
        dataSource.getGuestToken(userType, uniqueId)
}