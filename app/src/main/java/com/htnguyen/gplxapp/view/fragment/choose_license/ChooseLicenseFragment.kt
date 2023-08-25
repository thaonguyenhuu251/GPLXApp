package com.htnguyen.gplxapp.view.fragment.choose_license

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.FragmentChooseLicenseBinding
import com.htnguyen.gplxapp.databinding.FragmentHomeBinding
import com.htnguyen.gplxapp.databinding.FragmentInformationAppBinding
import com.htnguyen.gplxapp.view.activity.MainActivity
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.base.utils.BaseConst
import com.htnguyen.gplxapp.view.fragment.information_app.InformationAppFragment
import com.htnguyen.gplxapp.view.fragment.introduction_app.IntroductionAppFragment
import com.htnguyen.smartcalculator.base.utils.SharePreference
import java.util.*
import kotlin.concurrent.timerTask


class ChooseLicenseFragment : BaseFragment<FragmentChooseLicenseBinding>() {

    var fromHome: String = ""
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentChooseLicenseBinding {
        binding = FragmentChooseLicenseBinding.inflate(layoutInflater, container, false)
        return binding
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentChooseLicenseBinding) {

        binding.apply {
            val bundle = arguments
            if (bundle != null) {
                fromHome = bundle.getString(BaseConst.ARG_FROM_HOME).toString()
                if (fromHome.isEmpty()) {
                    btnBack.visibility = View.GONE
                } else {
                    btnBack.visibility = View.VISIBLE
                    var license = SharePreference.getStringPref(requireContext(), "license")
                    if (license == "A1") {
                        icCheckA1.visibility = View.VISIBLE
                        icCheckA2.visibility = View.INVISIBLE
                    } else {
                        icCheckA1.visibility = View.INVISIBLE
                        icCheckA2.visibility = View.VISIBLE
                    }
                }
            }

            btnLicenseA1.setOnClickListener {
                icCheckA1.visibility = View.VISIBLE
                icCheckA2.visibility = View.INVISIBLE
                SharePreference.setStringPref(requireContext(), "license", "A1")
                if (fromHome.isEmpty()) {
                    Timer().schedule(timerTask {
                        replaceFragment(InformationAppFragment(), R.id.container, false)
                    }, 1000)
                } else {
                    onClickBack()
                }

            }

            btnLicenseA2.setOnClickListener {
                icCheckA1.visibility = View.INVISIBLE
                icCheckA2.visibility = View.VISIBLE
                SharePreference.setStringPref(requireContext(), "license", "A2")
                if (fromHome.isEmpty()) {
                    Timer().schedule(timerTask {
                        replaceFragment(InformationAppFragment(), R.id.container, false)
                    }, 1000)
                } else {
                    onClickBack()
                }
            }

            btnBack.setOnClickListener {
                onClickBack()
            }
        }
    }

}