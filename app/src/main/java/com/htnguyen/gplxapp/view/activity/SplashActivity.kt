package com.htnguyen.gplxapp.view.activity

import android.content.Context
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.databinding.ActivityMainBinding
import com.htnguyen.gplxapp.databinding.ActivitySplashBinding
import com.htnguyen.gplxapp.view.base.BaseActivity
import com.htnguyen.gplxapp.view.base.textview.fall.FallTextView
import com.htnguyen.gplxapp.view.base.textview.htext.AnimationListener
import com.htnguyen.gplxapp.view.base.textview.htext.HTextView

class SplashActivity : BaseActivity() {
    private lateinit var bindings: ActivitySplashBinding
    override fun getBindingView(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?, binding: ViewBinding) {
        binding.root.findViewById<FallTextView>(R.id.fallTextHeader).animateText("ÔN THI GPLX MÁY")
        binding.root.findViewById<FallTextView>(R.id.fallTextHeader).setAnimationListener(AnimationListener{

        })
    }

    internal class SimpleAnimationListener(
        private val context: Context,
        private var binding: ViewBinding
    ) : AnimationListener {
        override fun onAnimationEnd(hTextView: HTextView?) {
            binding.root.findViewById<FallTextView>(R.id.fallTextContent).animateText("PHÍA TAY LÁI LÀ SỰ SỐNG,\n HÃY LÁI XE BẰNG CẢ TRÁI TIM")
        }
    }
}