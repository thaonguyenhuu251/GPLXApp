package com.htnguyen.gplxapp.view.fragment.exam_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.databinding.FragmentExamResultBinding

class ExamResultFragment : BaseFragment<FragmentExamResultBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentExamResultBinding {
        return FragmentExamResultBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.lifecycleOwner = activity
    }

    override fun initEvent() {
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExamResultBinding) {
    }

}