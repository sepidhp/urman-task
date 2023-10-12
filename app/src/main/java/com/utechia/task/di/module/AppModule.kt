package com.utechia.task.di.module

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.utechia.data.BuildConfig
import com.utechia.data.exceptions.NetworkExceptionHandler
import com.utechia.domain.socket.SocketHandler
import com.utechia.domain.util.Constants
import com.utechia.presentation.util.DispatchersProvider
import com.utechia.presentation.util.DispatchersProviderImpl
import com.utechia.presentation.util.TokenProvider
import com.utechia.presentation.util.UniqueIdProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun resources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(Constants.TAG, MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun okHttpClient(baseHttpClient: BaseHttpClient): OkHttpClient = baseHttpClient.okHttpClient

    @Provides
    @Singleton
    fun retrofit(baseRetrofit: BaseRetrofit): Retrofit = baseRetrofit.retrofit

    @Provides
    @Singleton
    fun dispatcher(dispatchersProvider: DispatchersProviderImpl): DispatchersProvider =
        dispatchersProvider.dispatchersProvider

    @Provides
    @Singleton
    fun apiExceptionHandler(gson: Gson): NetworkExceptionHandler = NetworkExceptionHandler(gson)

    @Provides
    @Singleton
    fun provideGlide(context: Application): RequestManager {
        return Glide.with(context)
    }

    @Provides
    @Singleton
    fun provideSocket(): Socket {
        val options = IO.Options()
        options.transports = arrayOf(WebSocket.NAME)
        return IO.socket(BuildConfig.SOCKET_URL, options)
    }

    @Provides
    @Singleton
    fun provideSocketHandler(socket: Socket) = SocketHandler(socket)

    @Provides
    @Singleton
    fun provideTokenProvider(sharedPreferences: SharedPreferences) =
        TokenProvider(sharedPreferences)

    @Provides
    @Singleton
    fun provideUniqueIdProvider(sharedPreferences: SharedPreferences) =
        UniqueIdProvider(sharedPreferences)
}