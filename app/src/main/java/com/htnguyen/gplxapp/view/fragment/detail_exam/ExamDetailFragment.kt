package com.htnguyen.gplxapp.view.fragment.detail_exam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.databinding.FragmentExamDetailBinding


class ExamDetailFragment : BaseFragment<FragmentExamDetailBinding>()  {
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentExamDetailBinding {
        return FragmentExamDetailBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initEvent() {

    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExamDetailBinding) {

    }


}