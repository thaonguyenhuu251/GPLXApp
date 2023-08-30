package com.htnguyen.gplxapp.base.customView.loading

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import com.htnguyen.gplxapp.databinding.WidgetLoadingBinding

class BaseLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    private var binding: WidgetLoadingBinding

    init {
        val rotate = RotateAnimation(
            0F,
            360F,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.repeatCount = Animation.INFINITE
        rotate.interpolator = LinearInterpolator()
        rotate.duration = 800
        binding = WidgetLoadingBinding.inflate(LayoutInflater.from(context), this, true)
        binding.imgRotate.startAnimation(rotate)
    }
}