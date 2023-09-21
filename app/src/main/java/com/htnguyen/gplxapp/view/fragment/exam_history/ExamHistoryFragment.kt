package com.htnguyen.gplxapp.view.fragment.exam_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.utils.observe
import com.htnguyen.gplxapp.base.utils.parseJsonToListExam
import com.htnguyen.gplxapp.base.utils.parseJsonToListExamHistory
import com.htnguyen.gplxapp.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.databinding.FragmentExamBinding
import com.htnguyen.gplxapp.databinding.FragmentExamHistoryBinding
import com.htnguyen.gplxapp.model.ExamDetail
import com.htnguyen.gplxapp.model.ExamHistory
import com.htnguyen.gplxapp.model.StatusExam
import com.htnguyen.gplxapp.view.adapter.ExamDetailAdapter
import com.htnguyen.gplxapp.view.adapter.ExamHistoryAdapter
import com.htnguyen.gplxapp.view.adapter.ExamHistoryAnswerAdapter
import com.htnguyen.gplxapp.view.adapter.ExamTableResultAdapter
import com.htnguyen.gplxapp.viewModels.ExamDetailViewModel
import com.htnguyen.gplxapp.viewModels.ExamHistoryViewModel
import java.util.ArrayList

class ExamHistoryFragment : BaseFragment<FragmentExamHistoryBinding>() {

    private val adapter = ExamHistoryAdapter()
    var idExam: Int = -1
    private val examHistoryViewModel by viewModels<ExamHistoryViewModel>()
    var listExamHistory: ArrayList<ExamHistory> = arrayListOf()
    var listStatusExam: ArrayList<StatusExam> = arrayListOf()

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentExamHistoryBinding {
        return FragmentExamHistoryBinding.inflate(layoutInflater)
    }

    override fun initData() {}

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            onClickBack()
        }
        binding.layoutManager.setOnClickListener {  }
    }

    override fun onBackPress() {}

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExamHistoryBinding) {
        val bundle = arguments
        if (bundle != null) {
            idExam = bundle.getInt("id_Exam")
        }
        with(examHistoryViewModel) {
            observe(responseStatusExam) { it ->
                listStatusExam = it?.filter {
                    it.idExam == idExam
                } as ArrayList<StatusExam>

                val json = readJSONFromAsset(requireContext(), "exam.json")
                listExamHistory = filterList(
                    parseJsonToListExamHistory(json),
                    listStatusExam
                ) as ArrayList<ExamHistory>
                adapter.setItems(listExamHistory)

            }

        }

        binding.rcvExam.adapter = adapter

    }

    private fun filterList(
        parseJsonToListExamHistory: Array<ExamHistory>,
        listStatusExam: ArrayList<StatusExam>
    ): List<ExamHistory> {
        val list: ArrayList<ExamHistory> = arrayListOf()
        for (statusExam in listStatusExam) {
            val examDetail = parseJsonToListExamHistory.first { it.id == statusExam.idAsk }
            examDetail.positionChoose = statusExam.positionChoose
            examDetail.isCorrectResult = statusExam.statusAsk == 1
            list.add(examDetail)
        }
        return list
    }
}