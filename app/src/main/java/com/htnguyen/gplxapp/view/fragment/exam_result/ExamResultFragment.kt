package com.htnguyen.gplxapp.view.fragment.exam_result

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.utils.observe
import com.htnguyen.gplxapp.databinding.FragmentExamResultBinding
import com.htnguyen.gplxapp.model.Exam
import com.htnguyen.gplxapp.view.fragment.home.HomeFragment
import com.htnguyen.gplxapp.viewModels.ExamResultViewModel

class ExamResultFragment : BaseFragment<FragmentExamResultBinding>() {
    var idExam: Int = -1
    lateinit var exam: Exam
    private val examResultViewModel by viewModels<ExamResultViewModel>()
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
        binding.btnDoNextExam.setOnClickListener {
            transitFragmentAnimation(HomeFragment(), R.id.container)
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExamResultBinding) {
        val bundle = arguments
        if (bundle != null) {
            idExam = bundle.getInt("id_Exam")
        }

        with(examResultViewModel) {
            observe(responseExam) { it ->
                if (it != null) {
                    exam = it.first { it.id == idExam }
                    genData()
                }
            }
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun genData() {
        if (exam.countCorrectQuestion!! > 20 && exam.paralysisPoint!! < 1) {
            binding.txtNameExam.text = exam.name
            binding.txtNameExam.setTextColor(resources.getColor(R.color.primary, null))
            binding.txtNotifiPointExam.visibility = View.GONE
            binding.txtNotifiResultFailExam.visibility = View.GONE
            binding.txtNotifiResultPassExam.visibility = View.VISIBLE
            binding.txtNumberCorrectResult.text = exam.countCorrectQuestion.toString()
            binding.txtNumberFailResult.text = exam.countFailQuestion.toString()
        } else if (exam.countCorrectQuestion!! > 20 && exam.paralysisPoint!! > 0 ){
            binding.txtNameExam.text = exam.name
            binding.txtNameExam.setTextColor(resources.getColor(R.color.text_red, null))
            binding.txtNotifiPointExam.text = "Bạn không vượt qua bài thi vì làm sai ${exam.paralysisPoint} câu hỏi điểm liệt"
            binding.txtNotifiResultFailExam.visibility = View.VISIBLE
            binding.txtNotifiResultPassExam.visibility = View.GONE
            binding.txtNumberCorrectResult.text = exam.countCorrectQuestion.toString()
            binding.txtNumberFailResult.text = exam.countFailQuestion.toString()
        } else if (exam.countCorrectQuestion!! < 21 && exam.paralysisPoint!! > 0 ) {
            binding.txtNameExam.text = exam.name
            binding.txtNameExam.setTextColor(resources.getColor(R.color.text_red, null))
            binding.txtNotifiPointExam.text = "Bạn không vượt qua bài thi vì làm sai ${exam.countFailQuestion} câu hỏi và làm sai cả câu điểm liệt"
            binding.txtNotifiResultFailExam.visibility = View.VISIBLE
            binding.txtNotifiResultPassExam.visibility = View.GONE
            binding.txtNumberCorrectResult.text = exam.countCorrectQuestion.toString()
            binding.txtNumberFailResult.text = exam.countFailQuestion.toString()
        } else {
            binding.txtNameExam.text = exam.name
            binding.txtNameExam.setTextColor(resources.getColor(R.color.text_red, null))
            binding.txtNotifiPointExam.text = "Bạn không vượt qua bài thi vì làm sai ${exam.countFailQuestion} câu hỏi"
            binding.txtNotifiResultFailExam.visibility = View.VISIBLE
            binding.txtNotifiResultPassExam.visibility = View.GONE
            binding.txtNumberCorrectResult.text = exam.countCorrectQuestion.toString()
            binding.txtNumberFailResult.text = exam.countFailQuestion.toString()
        }

    }

}