package com.htnguyen.gplxapp.view.fragment.learning
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.databinding.FragmentLearningBinding
import com.htnguyen.gplxapp.databinding.FragmentTipsBinding
import com.htnguyen.gplxapp.view.base.BaseFragment

class LearningFragment : BaseFragment<FragmentLearningBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentLearningBinding {
        return FragmentLearningBinding.inflate(layoutInflater)
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

    override fun initView(savedInstanceState: Bundle?, binding: FragmentLearningBinding) {

    }

}