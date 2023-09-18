package com.htnguyen.gplxapp.view.fragment.exam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.base.utils.BaseConst
import com.htnguyen.gplxapp.base.utils.observe
import com.htnguyen.gplxapp.base.utils.showChangeSizeDialog
import com.htnguyen.gplxapp.base.utils.showStartExamDialog
import com.htnguyen.gplxapp.databinding.FragmentExamBinding
import com.htnguyen.gplxapp.model.Exam
import com.htnguyen.gplxapp.view.adapter.ExamAdapter
import com.htnguyen.gplxapp.view.fragment.exam_detail.ExamDetailFragment
import com.htnguyen.gplxapp.view.fragment.exam_result.ExamResultFragment
import com.htnguyen.gplxapp.view.fragment.learning.ExamViewModel

class ExamFragment : BaseFragment<FragmentExamBinding>() {
    private val adapter = ExamAdapter()
    private val examViewModel by viewModels<ExamViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentExamBinding {
        return FragmentExamBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.lifecycleOwner = activity
        binding.rcvExam.adapter = adapter

        with(examViewModel) {
            observe(responseExam) {
                it?.let {
                    adapter.setItems(it)
                }
            }
        }
    }

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }

        adapter.sendDataItem = { i, model ->

            if (model != null) {
                if (model.completeExam == BaseConst.STATUS_NOT_DONE_EXAM || model.completeExam == BaseConst.STATUS_NOT_START_EXAM){
                    context?.showStartExamDialog(
                        onPositiveClickListener = {
                            val bundle = Bundle()
                            bundle.putInt("id_Exam", i+1)
                            if (model != null) {
                                model.time?.let { bundle.putLong("time_exam", it) }
                            }
                            transitFragmentAnimation(ExamDetailFragment(), R.id.container, bundle)
                        }
                    )
                } else {
                    val bundle = Bundle()
                    bundle.putInt("id_Exam", i+1)
                    transitFragmentAnimation(ExamResultFragment(), R.id.container, bundle)
                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExamBinding) {

    }

    override fun onBackPress() {}
}