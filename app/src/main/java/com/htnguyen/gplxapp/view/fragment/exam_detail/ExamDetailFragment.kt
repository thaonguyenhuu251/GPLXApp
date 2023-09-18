package com.htnguyen.gplxapp.view.fragment.exam_detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.utils.*
import com.htnguyen.gplxapp.databinding.FragmentExamDetailBinding
import com.htnguyen.gplxapp.model.*
import com.htnguyen.gplxapp.view.adapter.ExamDetailAdapter
import com.htnguyen.gplxapp.view.adapter.ExamTableResultAdapter
import com.htnguyen.gplxapp.view.fragment.exam_result.ExamResultFragment
import com.htnguyen.gplxapp.viewModels.ExamDetailViewModel
import java.text.FieldPosition
import java.util.*


class ExamDetailFragment : BaseFragment<FragmentExamDetailBinding>(), TextToSpeech.OnInitListener {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    var layoutBottomSheet: LinearLayout? = null
    var layoutManager: LinearLayoutManager? = null
    private val adapter = ExamDetailAdapter()
    private val adapterResult = ExamTableResultAdapter()
    private var tts: TextToSpeech? = null
    var idExam: Int = -1
    private val examDetailViewModel by viewModels<ExamDetailViewModel>()
    var listStatusExam: ArrayList<StatusExam> = arrayListOf()
    var listExamDetail: ArrayList<ExamDetail> = arrayListOf()
    var exam: Exam? = null
    var positionStatusExam = 0

    private val START_TIME_IN_MILLIS: Long = 1140000
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS

    var minutes = 0
    var seconds = 0
    var timeLeftFormatted = ""


    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentExamDetailBinding {
        return FragmentExamDetailBinding.inflate(layoutInflater)
    }

    override fun initData() {
        binding.lifecycleOwner = activity

    }

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            context?.showPauseExamDialog(
                onPositiveClickListener = {
                    UpdateExam(BaseConst.STATUS_NOT_DONE_EXAM, -1, -1, -1)
                    onClickBack()
                },
                onNegativeClickListener = {

                }
            )
        }

        binding.txtFinish.setOnClickListener {
            var isSuccesExam = true
            listStatusExam.forEach {
                if (it.statusAsk != 1 && it.statusAsk != -1) {
                    isSuccesExam = false
                }
            }
            if (isSuccesExam) {
                scoreExam()
            } else {
                Toast.makeText(context, "bạn chưa làm hết các câu hỏi", Toast.LENGTH_SHORT).show()
            }
        }

        adapter.doExamItem = { position, model, result, answerChoose, indexAnswer ->
            examDetailViewModel.updateStatusExam(
                StatusExam(
                    model.id,
                    idExam,
                    model.type,
                    result,
                    answerChoose,
                    model.result,
                    indexAnswer
                )
            )
        }

    }

    private fun scoreExam() {
        var countCorrectExam = 0
        var countFailQuestion = 0
        var paralysisPoint = 0
        listStatusExam.forEach { statusExam ->
            if (statusExam.statusAsk == 1) {
                countCorrectExam += 1
            }
            if ((statusExam.statusAsk == -1 && statusExam.idType == 0) ||
                (statusExam.statusAsk == -1 && statusExam.idType == 10)||
                    (statusExam.statusAsk == -1 && statusExam.idType == 20)||
                    (statusExam.statusAsk == -1 && statusExam.idType == 30)||
                    (statusExam.statusAsk == -1 && statusExam.idType == 40) ||
                    (statusExam.statusAsk == -1 && statusExam.idType == 50)){
                paralysisPoint += 1
            }
        }
        if(countCorrectExam >20 && paralysisPoint <1 ){
            UpdateExam(
                BaseConst.STATUS_DONE_EXAM,
                countCorrectExam,
                25 - countCorrectExam,
                paralysisPoint
            )
            context?.showFinishExamDialog(
                onPositiveClickListener = {
                    val bundle = Bundle()
                    bundle.putInt("id_Exam", idExam)
                    transitFragmentAnimation(ExamResultFragment(), R.id.container, bundle)
                }
            )
        } else {
            UpdateExam(
                BaseConst.STATUS_FAIL_EXAM,
                countCorrectExam,
                25 - countCorrectExam,
                paralysisPoint
            )
            context?.showFinishExamDialog(
                onPositiveClickListener = {
                    val bundle = Bundle()
                    bundle.putInt("id_Exam", idExam)
                    transitFragmentAnimation(ExamResultFragment(), R.id.container, bundle)
                }
            )
        }

    }

    private fun filterList(
        parseJsonToListExam: Array<ExamDetail>,
        listStatusExam: ArrayList<StatusExam>
    ): List<ExamDetail> {
        val list: ArrayList<ExamDetail> = arrayListOf()
        for (statusExam in listStatusExam) {
            val examDetail = parseJsonToListExam.first { it.id == statusExam.idAsk }
            examDetail.positionChoose = statusExam.positionChoose
            examDetail.isCorrectResult = statusExam.statusAsk == 1
            list.add(examDetail)
        }
        return list
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExamDetailBinding) {
        val bundle = arguments
        if (bundle != null) {
            idExam = bundle.getInt("id_Exam")
            mTimeLeftInMillis = bundle.getLong("time_exam")
            if (mTimeLeftInMillis.toInt() < 1000) {
                mTimeLeftInMillis = START_TIME_IN_MILLIS
            }
        }

        tts = TextToSpeech(requireContext(), this)
        setBottomSheetBehavior()
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                UpdateExam(BaseConst.STATUS_FAIL_EXAM, -1, -1, -1)
                onClickBack()
                Toast.makeText(context, mTimeLeftInMillis.toString(), Toast.LENGTH_LONG).show()
            }
        }.start()

        with(examDetailViewModel) {
            observe(responseStatusExam) { it ->
                listStatusExam = it?.filter {
                    it.idExam == idExam
                } as ArrayList<StatusExam>
                if (positionStatusExam < 0) {
                    listStatusExam[0].isSelected = true
                } else {
                    listStatusExam[positionStatusExam].isSelected = true
                }

                adapterResult.setItems(listStatusExam)

                val json = readJSONFromAsset(requireContext(), "exam.json")
                listExamDetail = filterList(
                    parseJsonToListExam(json),
                    listStatusExam
                ) as ArrayList<ExamDetail>
                adapter.setItems(listExamDetail)

            }

        }

        binding.viewPager.adapter = adapter
        adapterResult.onClickItem = { position, view ->
            binding.viewPager.setCurrentItem(position, false)
            positionStatusExam = position
            setTextPageCurrent(position + 1)
        }
        binding.layoutBottomsheet.rcvList.adapter = adapterResult


        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val exam = listExamDetail[position]
                var text = exam.ask
                exam.answer_list?.forEachIndexed { index, s ->
                    if (s.isNotEmpty()) {
                        val result = "Đáp án ${index + 1} ${s}"
                        text += result
                    }

                }
                text?.let { speakOut(it) }
                setTextPageCurrent(position + 1)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setBottomSheetBehavior() {
        var positionNextPageExam = 0
        var positionPreviousPageExam = 0
        layoutBottomSheet = binding.layoutBottomsheet.bottomSheet
        binding.layoutBottomsheet.rcvList.layoutManager = GridLayoutManager(requireContext(), 5)
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet!!).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        (bottomSheetBehavior as BottomSheetBehavior<*>).addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    binding.lnTopbar.visibility = View.GONE
                    binding.viewOpenBottomSheet.visibility = View.VISIBLE
                    binding.layoutBottomsheet.imgShow.setImageResource(R.drawable.ic_drop_down_white)
                } else {
                    binding.lnTopbar.visibility = View.VISIBLE
                    binding.viewOpenBottomSheet.visibility = View.GONE
                    binding.layoutBottomsheet.imgShow.setImageResource(R.drawable.ic_drop_up)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        binding.layoutBottomsheet.bottomSheet.setOnClickListener { v: View? ->
            if ((bottomSheetBehavior as BottomSheetBehavior<*>).state != BottomSheetBehavior.STATE_EXPANDED) {
                (bottomSheetBehavior as BottomSheetBehavior<*>).state =
                    BottomSheetBehavior.STATE_EXPANDED
            } else {
                (bottomSheetBehavior as BottomSheetBehavior<*>).state =
                    BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        binding.layoutBottomsheet.imgNext.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
            positionNextPageExam = (binding.viewPager.currentItem + 1)
            setTextPageCurrent(positionNextPageExam)
        }

        binding.layoutBottomsheet.imgPrevious.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
            positionPreviousPageExam =
                positionNextPageExam - (positionNextPageExam - binding.viewPager.currentItem) + 1
            setTextPageCurrent(positionPreviousPageExam)
        }

        binding.viewOpenBottomSheet.setOnClickListener {
            (bottomSheetBehavior as BottomSheetBehavior<*>).state =
                BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val locale = Locale("vi", "VN")
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

            } else {

            }
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun updateCountDownText() {
        minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        timeLeftFormatted = String.format(Locale.getDefault(), "%02dp:%02ds", minutes, seconds)
        binding.txtCountDownTimer.text = timeLeftFormatted
    }

    private fun setTextPageCurrent(position: Int) {
        binding.layoutBottomsheet.txtAsk.text = "Câu $position/25"
        if (listStatusExam.isNotEmpty()) {
            listStatusExam.forEach {
                it.isSelected = false
            }
            listStatusExam[position - 1].isSelected = true
            adapterResult.updateItems(listStatusExam)
        }
    }

    private fun UpdateExam(
        completeExam: Int,
        countCorrectExam: Int,
        countFailQuestion: Int,
        paralysisPoint: Int
    ) {
        examDetailViewModel.updateExam(
            Exam(
                idExam,
                "Đề số ${idExam}",
                "Còn $timeLeftFormatted",
                mTimeLeftInMillis,
                completeExam,
                countCorrectExam,
                countFailQuestion,
                paralysisPoint
            )
        )
    }

    override fun onDestroy() {

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onBackPress() {}


}