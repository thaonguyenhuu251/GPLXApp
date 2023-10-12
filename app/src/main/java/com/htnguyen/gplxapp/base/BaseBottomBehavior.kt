package com.htnguyen.gplxapp.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.htnguyen.gplxapp.R


abstract class BaseBottomBehavior<B : ViewBinding> : BottomSheetDialogFragment() {
    private var _binding: B? = null
    val binding get() = _binding!!
    override fun getTheme(): Int = R.style.BaseBottomSheetDialog
    override fun isCancelable(): Boolean = true


    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialog ->
            /*val d = dialog as BottomSheetDialog
            val llBottomSheet = d.findViewById<FrameLayout>(R.id.frameBottomSheet)
            BottomSheetBehavior.from(llBottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED*/
        }

        return dialog
    }
    abstract fun initView(inflater: LayoutInflater, container: ViewGroup?, binding: B)
    abstract fun getDialogBinding(inflater: LayoutInflater, container: ViewGroup?): B


    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}