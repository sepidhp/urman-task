package com.utechia.task.di.module

import com.utechia.data.repository.UserRepositoryImpl
import com.utechia.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.utechia.data.repository.UserRemoteDataSource

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    fun provideUserRepository(remoteDataSource: UserRemoteDataSource): UserRepository =
        UserRepositoryImpl(remoteDataSource)
}
