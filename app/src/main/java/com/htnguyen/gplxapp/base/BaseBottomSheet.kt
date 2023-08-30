package com.htnguyen.gplxapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.htnguyen.gplxapp.R

open class BaseBottomSheet(
    @LayoutRes private val layoutRes: Int? = 0,
    private val corneredTheme: Int? = R.style.MyBottomSheetStyle,
    private val normalTheme: Int? = R.style.MyBottomSheetStyle,
    private val isCornered: Boolean? = false,
    private val onBind: (View, binding: ViewDataBinding) -> Unit,
) : BottomSheetDialogFragment() {

    //REMINDER :  your layout must have data binding syntax <layout> </layout> to avoid null exception...

    lateinit var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isCornered == true) {
            setStyle(STYLE_NORMAL, corneredTheme!!)
        } else setStyle(STYLE_NORMAL, normalTheme!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            return if (layoutRes == 0) {
                dismiss()
                null
            } else {
                binding = DataBindingUtil.inflate(inflater, layoutRes!!, container, false)
                /*dialog?.setOnShowListener { dialog ->
                    val sheetDialog = dialog as BottomSheetDialog
                    val bottomSheet = sheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
                    val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
                    setupHeight(bottomSheet)
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    bottomSheetBehavior.isFitToContents = true
                    bottomSheetBehavior.isDraggable = true
                    bottomSheetBehavior.isHideable = true
                }*/
                binding.root
            }
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Opps,You forget to convert ur layout to data binding layout ^_^",
                Toast.LENGTH_LONG
            ).show()
            return null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind(view, binding)
    }

    private fun setupHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        bottomSheet.layoutParams = layoutParams
    }

}