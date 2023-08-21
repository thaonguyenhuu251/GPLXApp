package com.htnguyen.gplxapp.view.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.databinding.ActivitySplashBinding
import com.htnguyen.gplxapp.view.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun getBindingView(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?, binding: ViewBinding) {

    }
}