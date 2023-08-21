package com.htnguyen.gplxapp.view.activity
import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.databinding.ActivityMainBinding
import com.htnguyen.gplxapp.view.base.BaseActivity


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun getBindingView(): ViewBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?, binding: ViewBinding) {

    }

}