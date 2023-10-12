package com.utechia.task.application

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.utechia.presentation.util.UniqueIdProvider
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

@HiltAndroidApp
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)


    }
}