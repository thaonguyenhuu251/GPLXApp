package com.htnguyen.gplxapp.view.base


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.htnguyen.gplxapp.R

abstract class BaseCustomBottomSheet<B : ViewBinding> : BottomSheetDialogFragment() {
    private var _binding: B? = null
    val binding get() = _binding!!
    override fun getTheme(): Int = R.style.BaseBottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getDialogBinding(inflater, container)
        initView(inflater, container, binding)
        return binding.root
    }

    abstract fun initView(inflater: LayoutInflater, container: ViewGroup?, binding: B)
    abstract fun getDialogBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        return object : BottomSheetDialog(requireActivity(), theme) {
            override fun dispatchTouchEvent(motionEvent: MotionEvent): Boolean {
                if (currentFocus != null) {
                    val inputMethodManager: InputMethodManager =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                }
                return super.dispatchTouchEvent(motionEvent)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}