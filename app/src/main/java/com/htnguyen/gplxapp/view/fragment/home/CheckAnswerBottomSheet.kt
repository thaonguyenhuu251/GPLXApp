package com.htnguyen.gplxapp.view.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.base.BaseBottomBehavior
import com.htnguyen.gplxapp.databinding.FragmentPolicyBottomSheetBinding

class CheckAnswerBottomSheet : BaseBottomBehavior<FragmentPolicyBottomSheetBinding>() {
    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        binding: FragmentPolicyBottomSheetBinding
    ) {

    }

    override fun getDialogBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPolicyBottomSheetBinding {
        return FragmentPolicyBottomSheetBinding.inflate(layoutInflater)
    }


}