package com.htnguyen.gplxapp.view.fragment.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.FragmentSettingBinding
import com.htnguyen.gplxapp.view.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSettingBinding) {

    }

}