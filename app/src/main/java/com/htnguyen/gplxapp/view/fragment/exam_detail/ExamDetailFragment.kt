package com.htnguyen.gplxapp.view.fragment.exam_detail
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.utils.parseJsonToListExam
import com.htnguyen.gplxapp.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.databinding.FragmentExamDetailBinding
import com.htnguyen.gplxapp.model.ExamDetail
import com.htnguyen.gplxapp.view.adapter.ExamDetailAdapter
import java.util.*


class ExamDetailFragment : BaseFragment<FragmentExamDetailBinding>(), TextToSpeech.OnInitListener {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    var layoutBottomSheet: LinearLayout? = null
    var layoutManager: LinearLayoutManager? = null
    private val adapter = ExamDetailAdapter()
    private var tts: TextToSpeech? = null
    var idExam : Int = -1
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
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }

        adapter.nextItem = {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
        }

    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExamDetailBinding) {
        tts = TextToSpeech(requireContext(), this)
        setBottomSheetBehavior()
        val json = readJSONFromAsset(requireContext(), "exam.json" )
        val list = parseJsonToListExam(json)
        binding.viewPager.adapter = adapter
        val bundle = arguments
        if (bundle != null) {
            idExam = bundle.getInt("id_Exam")
        }
        var listExam = ArrayList<ExamDetail>()
        list.forEach {
            if (it.id_exam == idExam){
                listExam.add(it)
            }
        }
        adapter.setItems(listExam.toList())

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val exam = list[position]
                var text = exam.ask
                exam.answer_list?.forEachIndexed { index, s ->
                    if (s.isNotEmpty()) {
                        val result = "Đáp án ${index + 1} ${s}"
                        text += result
                    }

                }
                text?.let { speakOut(it) }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

    private fun setBottomSheetBehavior() {
        layoutBottomSheet = binding.layoutBottomsheet.bottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet!!).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        (bottomSheetBehavior as BottomSheetBehavior<*>).addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        binding.layoutBottomsheet.bottomSheet.setOnClickListener { v: View? ->
            if ((bottomSheetBehavior as BottomSheetBehavior<*>).state != BottomSheetBehavior.STATE_EXPANDED) {
                (bottomSheetBehavior as BottomSheetBehavior<*>).state = BottomSheetBehavior.STATE_EXPANDED
                binding.lnTopbar.visibility = View.GONE
                binding.layoutBottomsheet.imgShow.setImageResource(R.drawable.ic_drop_down_white)
            } else {
                (bottomSheetBehavior as BottomSheetBehavior<*>).state = BottomSheetBehavior.STATE_COLLAPSED
                binding.lnTopbar.visibility = View.VISIBLE
                binding.layoutBottomsheet.imgShow.setImageResource(R.drawable.ic_drop_up)
            }
        }

        binding.layoutBottomsheet.imgNext.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
        }

        binding.layoutBottomsheet.imgPrevious.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
        }

        binding.layoutBottomsheet.rcvList.adapter
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
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun onDestroy() {

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}