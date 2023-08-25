package com.htnguyen.gplxapp.view.fragment.tips
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.databinding.FragmentTipsBinding
import com.htnguyen.gplxapp.view.base.BaseFragment

class TipsFragment : BaseFragment<FragmentTipsBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentTipsBinding {
        return FragmentTipsBinding.inflate(layoutInflater)
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

    override fun initView(savedInstanceState: Bundle?, binding: FragmentTipsBinding) {

    }

}