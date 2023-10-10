package com.utechia.data.repository

import com.utechia.domain.model.*
import com.utechia.domain.util.Result

interface UserDataSource {
    suspend fun getExchanges(): Result<List<Exchange>>
}