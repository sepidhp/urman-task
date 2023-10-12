package com.utechia.data.repository

import com.utechia.domain.util.Result

interface AuthDataSource {
    suspend fun getGuestToken(userType: String, uniqueId: String): Result<String>
}