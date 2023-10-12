package com.utechia.domain.usecase

import com.utechia.domain.repository.AuthRepository
import com.utechia.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTokenUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(userType: String, uniqueId: String): Result<String> =
        repository.getGuestToken(userType, uniqueId)
}