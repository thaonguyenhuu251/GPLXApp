package com.htnguyen.gplxapp.view.fragment.exam
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.base.utils.observe
import com.htnguyen.gplxapp.databinding.FragmentExamBinding
import com.htnguyen.gplxapp.model.Exam
import com.htnguyen.gplxapp.view.fragment.learning.ExamViewModel

class ExamFragment : BaseFragment<FragmentExamBinding>() {
    private val adapter = BaseRecyclerViewAdapter<Exam>()
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

        examViewModel.addData()

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
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExamBinding) {

    }

}