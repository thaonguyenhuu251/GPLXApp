package com.htnguyen.gplxapp.view.fragment.learning
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.BaseBottomSheet
import com.htnguyen.gplxapp.base.BaseFragment
import com.htnguyen.gplxapp.databinding.FragmentLearningDetailBinding
import com.htnguyen.gplxapp.databinding.LayoutBottomsheetCheckAnswerBinding

class LearningDetailFragment : BaseFragment<FragmentLearningDetailBinding>() {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    var layoutBottomSheet: LinearLayout? = null
    var layoutManager: LinearLayoutManager? = null
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
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentLearningDetailBinding) {
        val changePasswordSheet = BaseBottomSheet(
            layoutRes = R.layout.layout_bottomsheet_check_answer,
            corneredTheme = R.style.MyBottomSheetStyle,
            isCornered = true,
            onBind = { view, _binding ->
                val bind = _binding as LayoutBottomsheetCheckAnswerBinding
                with(bind) {
                    // do what you want
                }
            })
        changePasswordSheet.show(childFragmentManager, "")
        //setBottomSheetBehavior()
    }

    private fun setBottomSheetBehavior() {
        /*layoutBottomSheet = binding.layoutBottomsheet.bottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet!!)
        (bottomSheetBehavior as BottomSheetBehavior<*>).addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        binding.layoutBottomsheet.txtTitle.setOnClickListener { v: View? ->
            if ((bottomSheetBehavior as BottomSheetBehavior<*>).state != BottomSheetBehavior.STATE_EXPANDED) {
                (bottomSheetBehavior as BottomSheetBehavior<*>).setState(BottomSheetBehavior.STATE_EXPANDED)
            } else {
                (bottomSheetBehavior as BottomSheetBehavior<*>).setState(BottomSheetBehavior.STATE_COLLAPSED)
            }
        }*/
    }

}