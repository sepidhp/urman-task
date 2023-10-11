package com.utechia.domain.usecase

import com.utechia.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton
import com.utechia.domain.util.Result

@Singleton
class GetTokenUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(userType: String, uniqueId: String): Result<String> =
        repository.getGuestToken(userType, uniqueId)
}