package com.utechia.presentation.util

import android.app.Activity
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat

class Utils {
    companion object {
        fun setStatusBarGradiant(activity: Activity, drawableId: Int) {
            val window: Window = activity.window
            val background =
                ContextCompat.getDrawable(activity, drawableId)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            window.statusBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
            window.navigationBarColor =
                ContextCompat.getColor(activity, android.R.color.transparent)
            window.setBackgroundDrawable(background)
        }

        fun calculateScreenWidth(activity: Activity): Int {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.widthPixels - 150
        }
    }
}