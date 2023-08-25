package com.htnguyen.gplxapp.view.activity
import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.ActivityMainBinding
import com.htnguyen.gplxapp.view.base.BaseActivity
import com.htnguyen.gplxapp.view.fragment.choose_license.ChooseLicenseFragment
import com.htnguyen.gplxapp.view.fragment.home.HomeFragment
import com.htnguyen.gplxapp.view.fragment.information_app.InformationAppFragment
import com.htnguyen.smartcalculator.base.utils.SharePreference


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun getBindingView(): ViewBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?, binding: ViewBinding) {
        if (SharePreference.getBooleanPref(this,"isFirstOpenApp")){
            replaceFragment(ChooseLicenseFragment(), R.id.container)
        }else{
            replaceFragment(HomeFragment(), R.id.container)
        }

    }

}