package com.utechia.presentation.util

import android.content.SharedPreferences
import com.utechia.domain.util.Constants
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

class UniqueIdProvider @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getUniqueId(): String {
        return sharedPreferences.getString(Constants.UNIQUE_ID, null) ?: generateUniqueId()
    }

    private fun generateUniqueId(): String {

        val uniqueId = UUID.randomUUID().toString()

        // persist
        sharedPreferences.edit()
            .putString(Constants.UNIQUE_ID, uniqueId)
            .apply()

        return uniqueId
    }
}