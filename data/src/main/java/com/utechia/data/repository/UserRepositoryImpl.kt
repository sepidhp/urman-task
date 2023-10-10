package com.utechia.data.repository

import com.utechia.domain.model.*
import com.utechia.domain.repository.UserRepository
import com.utechia.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val dataSource: UserDataSource) :
    UserRepository {
    override suspend fun getExchanges(): Result<List<Exchange>> =  dataSource.getExchanges()
}