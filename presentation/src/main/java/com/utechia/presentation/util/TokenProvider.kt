package com.utechia.presentation.util

import android.content.SharedPreferences
import com.utechia.domain.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

class TokenProvider @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun persistToken(token: String) {
        sharedPreferences.edit()
            .putString(Constants.TOKEN, token)
            .apply()
    }

    fun getToken() =
        sharedPreferences.getString(Constants.TOKEN, null)
}