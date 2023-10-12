package com.utechia.task.di.module

import com.utechia.data.BuildConfig.DEBUG
import com.utechia.presentation.util.TokenProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TIMEOUT = 30L

@Singleton
class BaseHttpClient @Inject constructor(
    private val tokenProvider: TokenProvider
) {
    val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level =
                    if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        )
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .build()

    private fun headersInterceptor() = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
        newRequest.header("Authorization", "Bearer ${tokenProvider.getToken()}")
        chain.proceed(newRequest.build())
    }
}