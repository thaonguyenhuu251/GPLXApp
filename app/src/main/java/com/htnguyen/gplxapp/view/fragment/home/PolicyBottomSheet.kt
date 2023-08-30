package com.htnguyen.gplxapp.view.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.databinding.FragmentPolicyBottomSheetBinding
import com.htnguyen.gplxapp.view.base.BaseCustomBottomSheet

class PolicyBottomSheet : BaseCustomBottomSheet<FragmentPolicyBottomSheetBinding>() {

    override fun getDialogBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPolicyBottomSheetBinding {
        return FragmentPolicyBottomSheetBinding.inflate(layoutInflater)
    }
    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        binding: FragmentPolicyBottomSheetBinding
    ) {

    }


}