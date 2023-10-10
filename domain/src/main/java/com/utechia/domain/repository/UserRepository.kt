package com.utechia.domain.repository

import com.utechia.domain.model.*
import com.utechia.domain.util.Result

interface UserRepository {
    suspend fun getExchanges(): Result<List<Exchange>>
}