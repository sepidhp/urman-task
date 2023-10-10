package com.utechia.presentation

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import com.utechia.presentation.base.BaseActivity
import com.utechia.presentation.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}