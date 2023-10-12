package com.utechia.domain.usecase

import com.utechia.domain.model.ExchangeName
import com.utechia.domain.repository.ExchangeRepository
import com.utechia.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetExchangesUseCase @Inject constructor(private val repository: ExchangeRepository) {
    suspend operator fun invoke(): Result<List<ExchangeName>> =
        repository.getExchangeNames()
}