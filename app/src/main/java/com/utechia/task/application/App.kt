package com.utechia.task.application

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.utechia.domain.util.Constants
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

@HiltAndroidApp
class App : MultiDexApplication() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        // create unique id for call refresh token api
        if (sharedPreferences.getString(Constants.UNIQUE_ID, null).isNullOrEmpty())
            sharedPreferences.edit()
                .putString(Constants.UNIQUE_ID, UUID.randomUUID().toString())
                .apply()
    }
}