package com.htnguyen.gplxapp.view.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.ActivityMainBinding
import com.htnguyen.gplxapp.databinding.ActivitySplashBinding
import com.htnguyen.gplxapp.base.BaseActivity
import com.htnguyen.gplxapp.base.textview.fall.FallTextView
import com.htnguyen.gplxapp.base.textview.htext.AnimationListener
import com.htnguyen.gplxapp.base.textview.htext.HTextView
import com.htnguyen.gplxapp.view.fragment.home.HomeFragment
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : com.htnguyen.gplxapp.base.BaseActivity() {
    private lateinit var bindings: ActivitySplashBinding
    override fun getBindingView(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?, binding: ViewBinding) {
        binding.root.findViewById<com.htnguyen.gplxapp.base.textview.fall.FallTextView>(R.id.fallTextHeader).animateText("ÔN THI GPLX MÁY")
        binding.root.findViewById<com.htnguyen.gplxapp.base.textview.fall.FallTextView>(R.id.fallTextHeader).setAnimationListener(
            com.htnguyen.gplxapp.base.textview.htext.AnimationListener {

            })

        Timer().schedule(timerTask {
            openActivity(MainActivity::class.java, false)
        }, 2000)
    }

    internal class SimpleAnimationListener(
        private val context: Context,
        private var binding: ViewBinding
    ) : com.htnguyen.gplxapp.base.textview.htext.AnimationListener {
        override fun onAnimationEnd(hTextView: com.htnguyen.gplxapp.base.textview.htext.HTextView?) {
            binding.root.findViewById<com.htnguyen.gplxapp.base.textview.fall.FallTextView>(R.id.fallTextContent).animateText("PHÍA TAY LÁI LÀ SỰ SỐNG,\n HÃY LÁI XE BẰNG CẢ TRÁI TIM")
        }
    }
}