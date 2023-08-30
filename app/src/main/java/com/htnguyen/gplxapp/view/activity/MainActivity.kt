package com.htnguyen.gplxapp.view.activity

import android.os.Build
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.utils.SharePreference
import com.htnguyen.gplxapp.databinding.ActivityMainBinding
import com.htnguyen.gplxapp.service.RemindersManager
import com.htnguyen.gplxapp.view.fragment.choose_license.ChooseLicenseFragment
import com.htnguyen.gplxapp.view.fragment.home.HomeFragment


class MainActivity : com.htnguyen.gplxapp.base.BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun getBindingView(): ViewBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?, binding: ViewBinding) {
        adjustFontScale(resources.configuration, 0.5F + SharePreference.fontText/8.0F)
        if (SharePreference.isLoginFirst){
            replaceFragment(ChooseLicenseFragment(), R.id.container)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                checkNotification()
            }
            createNotificationChannel()
            RemindersManager.startReminder(this)
        } else {
            replaceFragment(HomeFragment(), R.id.container)
        }
    }

}