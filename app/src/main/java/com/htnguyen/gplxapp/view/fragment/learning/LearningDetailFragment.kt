package com.htnguyen.gplxapp.view.fragment.learning
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseBottomSheet
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import com.htnguyen.gplxapp.base.utils.parseJsonToListTrafficLearn
import com.htnguyen.gplxapp.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.databinding.FragmentLearningDetailBinding
import com.htnguyen.gplxapp.databinding.LayoutBottomsheetCheckAnswerBinding
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.model.TrafficsLearnDetail
import com.htnguyen.gplxapp.view.adapter.TraffigsLearnDetailAdapter
import java.util.Locale

class LearningDetailFragment : BaseFragment<FragmentLearningDetailBinding>(), TextToSpeech.OnInitListener {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    var layoutBottomSheet: LinearLayout? = null
    var layoutManager: LinearLayoutManager? = null
    private val adapter = TraffigsLearnDetailAdapter()
    private var tts: TextToSpeech? = null
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
        
        adapter.sendDataItem = { position, trafficsLearn ->
            if (position == binding.viewPager.currentItem) {
                var text = trafficsLearn?.ask
                trafficsLearn?.answer_list?.forEachIndexed { index, s ->
                    if (s.isNotEmpty()) {
                        val result = "Đáp án ${index + 1} ${s}"
                        text += result
                    }

                }
                text?.let { speakOut(it) }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentLearningDetailBinding) {
        tts = TextToSpeech(requireContext(), this)
        setBottomSheetBehavior()
        val json = readJSONFromAsset(requireContext(), "learn_traffic.json" )
        val list = parseJsonToListTrafficLearn(json)
        binding.viewPager.adapter = adapter
        adapter.setItems(list.toList())
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
                (bottomSheetBehavior as BottomSheetBehavior<*>).setState(BottomSheetBehavior.STATE_EXPANDED)
                binding.lnTopbar.visibility = View.GONE
            } else {
                (bottomSheetBehavior as BottomSheetBehavior<*>).setState(BottomSheetBehavior.STATE_COLLAPSED)
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