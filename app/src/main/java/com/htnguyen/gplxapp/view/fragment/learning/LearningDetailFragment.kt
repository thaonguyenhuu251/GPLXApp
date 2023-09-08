package com.htnguyen.gplxapp.view.fragment.learning

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.base.utils.BaseConst
import com.htnguyen.gplxapp.base.utils.observe
import com.htnguyen.gplxapp.base.utils.parseJsonToListTrafficLearn
import com.htnguyen.gplxapp.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.databinding.FragmentLearningDetailBinding
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.view.adapter.TraffigLearnResultAdapter
import com.htnguyen.gplxapp.view.adapter.TraffigsLearnDetailAdapter
import com.htnguyen.gplxapp.viewModels.LearningDetailViewModel
import java.util.*
import kotlin.collections.ArrayList


class LearningDetailFragment : BaseFragment<FragmentLearningDetailBinding>(),
    TextToSpeech.OnInitListener {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    var layoutBottomSheet: LinearLayout? = null
    var layoutManager: LinearLayoutManager? = null
    private val adapter = TraffigsLearnDetailAdapter()
    private val adapterResult = TraffigLearnResultAdapter()
    private var tts: TextToSpeech? = null
    private val learningViewModel by viewModels<LearningDetailViewModel>()
    var listStatusLearn: ArrayList<StatusLearn> = arrayListOf()
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentLearningDetailBinding {
        return FragmentLearningDetailBinding.inflate(layoutInflater)
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

    override fun initView(savedInstanceState: Bundle?, binding: FragmentLearningDetailBinding) {
        tts = TextToSpeech(requireContext(), this)
        setBottomSheetBehavior()
        val json = readJSONFromAsset(requireContext(), "learn_traffic.json")
        val list = parseJsonToListTrafficLearn(json).filter {
            it.type == arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE)
                    || it.type % 10 == arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE)
                    || (it.type / 10 == arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE) && it.type >= 10)
        }

        with(learningViewModel) {
            observe(responseTrafficLearn) {
                it?.let { trafficsLearns ->
                    val listFilter = trafficsLearns.first {
                        it.id == arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE)
                    }.statusLesson
                    listFilter.forEach {
                        listStatusLearn.add(StatusLearn(it.idAsk, it.statusAsk))
                    }
                    adapterResult.setItems(listStatusLearn)
                }
            }
        }

        binding.viewPager.adapter = adapter
        adapter.setItems(list)

        binding.layoutBottomsheet.rcvList.adapter = adapterResult
        adapterResult.setItems(listStatusLearn)

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val trafficsLearn = list[position]
                var text = trafficsLearn.ask
                trafficsLearn.answer_list?.forEachIndexed { index, s ->
                    if (s.isNotEmpty()) {
                        val result = "Đáp án ${index + 1} ${s}"
                        text += result
                    }

                }
                text?.let { speakOut(it) }
                binding.layoutBottomsheet.txtAsk.text =
                    resources.getString(R.string.label_number_ask, position + 1, list.size)
            }

        })
    }

    private fun setBottomSheetBehavior() {
        layoutBottomSheet = binding.layoutBottomsheet.bottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet!!).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        (bottomSheetBehavior as BottomSheetBehavior<*>).addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        binding.layoutBottomsheet.bottomSheet.setOnClickListener { v: View? ->
            if ((bottomSheetBehavior as BottomSheetBehavior<*>).state != BottomSheetBehavior.STATE_EXPANDED) {
                (bottomSheetBehavior as BottomSheetBehavior<*>).state =
                    BottomSheetBehavior.STATE_EXPANDED
                binding.lnTopbar.visibility = View.GONE
            } else {
                (bottomSheetBehavior as BottomSheetBehavior<*>).state =
                    BottomSheetBehavior.STATE_COLLAPSED
                binding.lnTopbar.visibility = View.VISIBLE
            }
        }

        binding.layoutBottomsheet.imgNext.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
        }

        binding.layoutBottomsheet.imgPrevious.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
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

    override fun onDestroy() {

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}