package com.htnguyen.gplxapp.view.fragment.learning
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.databinding.FragmentLearningDetailBinding

class LearningDetailFragment : BaseFragment<FragmentLearningDetailBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentLearningDetailBinding {
        return FragmentLearningDetailBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.lifecycleOwner = activity

    }

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentLearningDetailBinding) {

    }

}