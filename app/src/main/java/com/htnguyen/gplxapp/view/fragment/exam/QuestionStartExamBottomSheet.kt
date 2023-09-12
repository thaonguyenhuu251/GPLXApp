package com.htnguyen.gplxapp.view.fragment.exam

import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.base.BaseCustomBottomSheet
import com.htnguyen.gplxapp.databinding.FragmentPolicyBottomSheetBinding
import com.htnguyen.gplxapp.databinding.FragmentQuestionStartExamBottomSheetBinding

class QuestionStartExamBottomSheet : BaseCustomBottomSheet<FragmentQuestionStartExamBottomSheetBinding>() {

    override fun getDialogBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuestionStartExamBottomSheetBinding {
        return FragmentQuestionStartExamBottomSheetBinding.inflate(layoutInflater)
    }
    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        binding: FragmentQuestionStartExamBottomSheetBinding
    ) {

    }


}