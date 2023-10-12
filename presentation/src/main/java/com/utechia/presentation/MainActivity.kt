package com.utechia.presentation

import android.os.Bundle
import com.utechia.presentation.base.BaseActivity
import com.utechia.presentation.databinding.ActivityMainBinding
import com.utechia.presentation.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.setStatusBarGradiant(this, R.drawable.bg_rect_solid_gradient_flat)
    }
}