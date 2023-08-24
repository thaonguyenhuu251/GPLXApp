package com.htnguyen.gplxapp.view.fragment.information_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.FragmentHomeBinding
import com.htnguyen.gplxapp.databinding.FragmentInformationAppBinding
import com.htnguyen.gplxapp.view.activity.MainActivity
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.fragment.introduction_app.IntroductionAppFragment
import java.util.*
import kotlin.concurrent.timerTask


class InformationAppFragment : BaseFragment<FragmentInformationAppBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentInformationAppBinding {
        binding = FragmentInformationAppBinding.inflate(layoutInflater, container, false)
        return binding
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentInformationAppBinding) {
        Timer().schedule(timerTask {
            replaceFragment(IntroductionAppFragment(), R.id.container,false)
        }, 2000)
    }

}