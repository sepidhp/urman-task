package com.utechia.domain.repository

import com.utechia.domain.model.*
import com.utechia.domain.util.Result

interface UserRepository {
    suspend fun getGuestToken(userType: String, uniqueId: String): Result<String>
    suspend fun getExchanges(): Result<List<Exchange>>
}