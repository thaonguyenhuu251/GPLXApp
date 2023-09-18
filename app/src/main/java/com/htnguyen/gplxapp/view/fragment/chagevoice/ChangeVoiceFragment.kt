package com.htnguyen.gplxapp.view.fragment.chagevoice

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.databinding.FragmentChangeVoiceBinding
import com.htnguyen.gplxapp.model.ChangeVoice
import com.htnguyen.gplxapp.view.adapter.ChangeVoiceAdapter
import java.util.*

class ChangeVoiceFragment : BaseFragment<FragmentChangeVoiceBinding>(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private val adapter = ChangeVoiceAdapter()
    private var listVoice: List<ChangeVoice> = listOf (
        ChangeVoice(1, "Trần Nguyên Quân", "Bắc Giang", "https://scontent.fhan5-11.fna.fbcdn.net/v/t1.6435-9/43298282_1848960638534164_1585743011340353536_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=0debeb&_nc_ohc=cWBJzuIodG4AX_4Ce9Q&_nc_ht=scontent.fhan5-11.fna&oh=00_AfBYJQHhKT4c_i5vcsPit7g5B3Py-VpfLiDWQfUcJVcw8A&oe=65167BC6"),
        ChangeVoice(2, "Phan Hoàng Anh", "Hà Tĩnh", "https://scontent.fhan5-11.fna.fbcdn.net/v/t39.30808-6/294564034_4731100763656598_2080790076684454737_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=174925&_nc_ohc=DCgZThrtR1AAX_jQ_1K&_nc_ht=scontent.fhan5-11.fna&oh=00_AfCPavFAsD50F39_0iMnZ6IjkWK-QoXm4mN3Z7zGZcJESw&oe=64F330F3"),
        ChangeVoice(3, "Trần Đình Tuấn", "Nghệ An", "https://scontent.fhan5-11.fna.fbcdn.net/v/t39.30808-6/350103668_581490843839743_431457669371916212_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=fWHB2Nz---EAX-Bu1MK&_nc_ht=scontent.fhan5-11.fna&oh=00_AfAuQrcqwrFTjBH-h5JngCwuCldJuCbLM2LU4zw4ew8ebw&oe=64F407B9"),
        ChangeVoice(4, "Nguyễn Hũu Thảo", "Nghệ An", "https://scontent.fhan5-10.fna.fbcdn.net/v/t39.30808-6/298715368_3367052540196294_5844762072293446759_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=F6Qn44VxF3cAX9ktKZ_&_nc_ht=scontent.fhan5-10.fna&oh=00_AfBZuJ-drimCm328OEDqg6b88igBLTLP0_rRQPFBemXCaA&oe=64F38A6C"),
        ChangeVoice(5, "Ninh Thị Thu Trang", "Nam Định", "https://scontent.fhan5-8.fna.fbcdn.net/v/t39.30808-6/366893455_3626114617600660_8378813498509952761_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=174925&_nc_ohc=azxSfX3qnLcAX-Pc98y&_nc_ht=scontent.fhan5-8.fna&oh=00_AfBes-EAgRwQ0D9LBvrusYO2O1lawRCrLLICiz7o7dIdsA&oe=64F42CB8"),
        )
    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentChangeVoiceBinding {
        return FragmentChangeVoiceBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initEvent() {
        binding.imgBack.setOnClickListener {
            onClickBack()
        }

        binding.txtBack.setOnClickListener {
            onClickBack()
        }

        adapter.onClickTask = {
            speakOut()
        }
    }

    override fun onBackPress() {}

    override fun initView(savedInstanceState: Bundle?, binding: FragmentChangeVoiceBinding) {
        tts = TextToSpeech(requireContext(), this)
        binding.lifecycleOwner = activity
        binding.rcvList.adapter = adapter
        adapter.setItems(listVoice)
        Log.d("ThaoNH", adapter.itemCount.toString())
    }

    override fun onInit(status: Int) {
        val loc = Locale("vi", "VN")
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(loc)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

            } else {

            }
        }
    }
    private fun speakOut() {
        tts!!.speak("Xin chào bạn đến với ứng dụng học giấy phép lái xe của chúng tôi", TextToSpeech.QUEUE_FLUSH, null,"")
    }

    public override fun onDestroy() {
        // Shutdown TTS when
        // activity is destroyed
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }


}