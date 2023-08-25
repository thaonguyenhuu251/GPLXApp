package com.htnguyen.gplxapp.view.fragment.information_app

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.FragmentInformationAppBinding
import com.htnguyen.gplxapp.view.base.BaseFragment
import com.htnguyen.gplxapp.view.fragment.introduction_app.IntroductionAppFragment
import java.util.*
import kotlin.concurrent.timerTask
import android.os.Handler


class InformationAppFragment : BaseFragment<FragmentInformationAppBinding>() {

    private val originalText = "Đây là một đoạn văn bản mẫu để hiển thị lần lượt từng ký tự." +
            " Đây là một đoạn văn bản mẫu để hiển thị lần lượt từng ký tự."
    private var currentIndex = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun getViewBinding(
        inflater: LayoutInflater?,
        container: ViewGroup?
    ): FragmentInformationAppBinding {
        binding = FragmentInformationAppBinding.inflate(layoutInflater, container, false)
        return binding
    }

    override fun initData() {
    }

    override fun initEvent() {
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentInformationAppBinding) {
        animateText()
    }

    private fun animateText() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (currentIndex < originalText.length) {
                    binding.tvInformation.text = originalText.substring(0, currentIndex + 1)
                    currentIndex++
                    handler.postDelayed(this, 100)
                } else {
                    Timer().schedule(timerTask {
                        replaceFragment(IntroductionAppFragment(), R.id.container, false)
                    }, 2000)
                }
            }
        }, 100)
    }

}