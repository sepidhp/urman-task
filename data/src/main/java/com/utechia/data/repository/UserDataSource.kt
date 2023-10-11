package com.utechia.data.repository

import com.utechia.domain.model.*
import com.utechia.domain.util.Result

interface UserDataSource {
    suspend fun getGuestToken(userType: String, uniqueId: String): Result<String>
    suspend fun getExchanges(): Result<List<Exchange>>
}