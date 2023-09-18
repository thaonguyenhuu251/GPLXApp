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
import com.htnguyen.gplxapp.base.utils.BaseConst
import com.htnguyen.gplxapp.base.utils.observe
import com.htnguyen.gplxapp.base.utils.parseJsonToListTrafficLearn
import com.htnguyen.gplxapp.base.utils.readJSONFromAsset
import com.htnguyen.gplxapp.databinding.FragmentLearningDetailBinding
import com.htnguyen.gplxapp.model.StatusLearn
import com.htnguyen.gplxapp.model.TrafficsLearn
import com.htnguyen.gplxapp.model.TrafficsLearnDetail
import com.htnguyen.gplxapp.view.adapter.TrafficLearnResultAdapter
import com.htnguyen.gplxapp.view.adapter.TrafficsLearnDetailAdapter
import com.htnguyen.gplxapp.viewModels.LearningDetailViewModel
import java.util.*


class LearningDetailFragment : BaseFragment<FragmentLearningDetailBinding>(),
    TextToSpeech.OnInitListener {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    var layoutBottomSheet: LinearLayout? = null
    var layoutManager: LinearLayoutManager? = null
    private val adapter = TrafficsLearnDetailAdapter()
    private val adapterResult = TrafficLearnResultAdapter()
    private var tts: TextToSpeech? = null
    private val learningViewModel by viewModels<LearningDetailViewModel>()
    var listStatusLearn: ArrayList<StatusLearn> = arrayListOf()
    var listTrafficLearn: ArrayList<TrafficsLearnDetail> = arrayListOf()
    var trafficsLearn: TrafficsLearn? = null
    var indexCurrent = 0
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
            updateTrafficLearn()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
            updateTrafficLearn()
        }

        adapter.nextItem = { position, model, result, indexAnswer ->
            val statusLearn =
                learningViewModel.responseStatusLearn.value?.first { it.idAsk == model.id }
            learningViewModel.updateStatusLearn(
                StatusLearn(
                    model.id,
                    model.type,
                    result,
                    if (result == -1) true else statusLearn?.statusAskFail,
                    indexAnswer,
                     true
                )
            )
            updateTrafficLearn()
        }

        adapterResult.sendDataItem = { position: Int, trafficsLearn: StatusLearn? ->
            binding.viewPager.setCurrentItem(position, false)
        }

        binding.cbSpeakers.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!isChecked) {
                tts!!.stop()
            } else {
                if (listTrafficLearn.isNotEmpty()) generateSpeakText(binding.viewPager.currentItem)
            }
        }
    }

    private fun filterList(
        parseJsonToListTrafficLearn: Array<TrafficsLearnDetail>,
        listStatusLearn: ArrayList<StatusLearn>
    ): List<TrafficsLearnDetail> {
        val list: ArrayList<TrafficsLearnDetail> = arrayListOf()
        for (statusLearn in listStatusLearn) {
            val trafficsLearnDetail = parseJsonToListTrafficLearn.first { it.id == statusLearn.idAsk }
            trafficsLearnDetail.answerSelected = statusLearn.answerSelected
            trafficsLearnDetail.isAnswer = statusLearn.statusAsk == 1
            list.add(trafficsLearnDetail)
        }
        return list
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentLearningDetailBinding) {
        tts = TextToSpeech(requireContext(), this)

        setBottomSheetBehavior()
        with(learningViewModel) {
            observe(responseStatusLearn) {
                listStatusLearn = if (arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE) != -1) {
                    it?.filter {
                        it.idType == arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE)
                                || it.idType % 10 == arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE)
                                || (it.idType / 10 == arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE) && it.idType >= 10)
                    } as ArrayList<StatusLearn>
                } else {
                    it?.filter { it.statusAskFail == true } as ArrayList<StatusLearn>
                }
                adapterResult.setItems(listStatusLearn)

                val json = readJSONFromAsset(requireContext(), "learn_traffic.json")
                if (listStatusLearn.isNotEmpty()) {
                    listTrafficLearn = filterList(
                        parseJsonToListTrafficLearn(json),
                        listStatusLearn
                    ) as ArrayList<TrafficsLearnDetail>
                }
                adapter.setItems(listTrafficLearn)
                binding.viewPager.currentItem = listStatusLearn.indexOfFirst { it.isSelected == true }
                indexCurrent = binding.viewPager.currentItem
                if (it.isNotEmpty()) {
                    generateSpeakText(binding.viewPager.currentItem)
                }

            }

            observe(responseTrafficLearn) {
                if (arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE) != -1) {
                    trafficsLearn =
                        it?.first { it.id == arguments?.getInt(BaseConst.ARG_TRAFFIC_LEARN_TYPE) }
                    binding.txtBack.text = trafficsLearn?.name
                } else {
                    binding.txtBack.text = resources.getString(R.string.home_wrong_good)
                }
            }
        }

        binding.viewPager.adapter = adapter

        binding.layoutBottomsheet.rcvList.adapter = adapterResult
        adapterResult.setItems(listStatusLearn)

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                generateSpeakText(position)
                binding.layoutBottomsheet.txtAsk.text =
                    resources.getString(
                        R.string.label_number_ask,
                        position + 1,
                        listTrafficLearn.size
                    )

                learningViewModel.updateStatusLearn(
                    StatusLearn(
                        listStatusLearn[indexCurrent].idAsk,
                        listStatusLearn[indexCurrent].idType,
                        listStatusLearn[indexCurrent].statusAsk,
                        listStatusLearn[indexCurrent].statusAskFail,
                        listStatusLearn[indexCurrent].answerSelected,
                        false
                    )
                )

                learningViewModel.updateStatusLearn(
                    StatusLearn(
                        listStatusLearn[position].idAsk,
                        listStatusLearn[position].idType,
                        listStatusLearn[position].statusAsk,
                        listStatusLearn[position].statusAskFail,
                        listStatusLearn[position].answerSelected,
                        true
                    )
                )

                indexCurrent = position
            }
        })
    }

    private fun generateSpeakText(position: Int) {
        if (listTrafficLearn.isNotEmpty()) {
            val trafficsLearn = listTrafficLearn[position]
            var text = trafficsLearn.ask
            trafficsLearn.answer_list?.forEachIndexed { index, s ->
                if (s.isNotEmpty()) {
                    val result = "Đáp án ${index + 1} ${s}"
                    text += result
                }

            }
            text?.let { speakOut(it) }
        }

    }

    private fun setBottomSheetBehavior() {
        layoutBottomSheet = binding.layoutBottomsheet.bottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet!!).apply {
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        (bottomSheetBehavior as BottomSheetBehavior<*>).addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    binding.lnTopbar.visibility = View.GONE
                    binding.viewOpenBottomSheet.visibility = View.VISIBLE
                } else {
                    binding.lnTopbar.visibility = View.VISIBLE
                    binding.viewOpenBottomSheet.visibility = View.GONE
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
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
        }

        binding.layoutBottomsheet.imgPrevious.setOnClickListener {
            binding.viewPager.setCurrentItem(binding.viewPager.currentItem - 1, true)
        }

        binding.viewOpenBottomSheet.setOnClickListener {
            (bottomSheetBehavior as BottomSheetBehavior<*>).state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val locale = Locale("vi", "VN")
            val result = tts!!.setLanguage(locale)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

            } else {

            }
        }
    }

    private fun speakOut(text: String) {
        if (binding.cbSpeakers.isChecked) {
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    fun updateTrafficLearn() {
        trafficsLearn?.completeLesson = listStatusLearn.count { it.statusAsk == 1 }
        trafficsLearn?.let { learningViewModel.updateTrafficLearn(it) }
    }

    override fun onDestroy() {
        updateTrafficLearn()
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onBackPress() {}

}