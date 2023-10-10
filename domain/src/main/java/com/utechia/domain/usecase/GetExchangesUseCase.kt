package com.utechia.domain.usecase

import com.utechia.domain.model.*
import com.utechia.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton
import com.utechia.domain.util.Result

@Singleton
class GetExchangesUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(): Result<List<Exchange>> =
        repository.getExchanges()
}