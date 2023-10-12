package com.utechia.domain.repository

import com.utechia.domain.util.Result

interface AuthRepository {
    suspend fun getGuestToken(userType: String, uniqueId: String): Result<String>
}