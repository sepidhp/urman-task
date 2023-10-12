package com.utechia.task.di.module

import com.utechia.data.repository.AuthRemoteDataSource
import com.utechia.data.repository.AuthRepositoryImpl
import com.utechia.data.repository.ExchangeRemoteDataSource
import com.utechia.data.repository.ExchangeRepositoryImpl
import com.utechia.domain.repository.AuthRepository
import com.utechia.domain.repository.ExchangeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideUserRepository(remoteDataSource: ExchangeRemoteDataSource): ExchangeRepository =
        ExchangeRepositoryImpl(remoteDataSource)

    @Provides
    fun provideAuthRepository(remoteDataSource: AuthRemoteDataSource): AuthRepository =
        AuthRepositoryImpl(remoteDataSource)
}
