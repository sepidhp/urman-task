package com.utechia.domain.usecase

import com.utechia.domain.model.*
import com.utechia.domain.repository.ExchangeRepository
import javax.inject.Inject
import javax.inject.Singleton
import com.utechia.domain.util.Result

@Singleton
class GetExchangesUseCase @Inject constructor(private val repository: ExchangeRepository) {
    suspend operator fun invoke(): Result<List<Exchange>> =
        repository.getExchanges()
}