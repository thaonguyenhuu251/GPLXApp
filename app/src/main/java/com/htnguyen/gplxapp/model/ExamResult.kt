package com.htnguyen.gplxapp.model

import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseItem
import java.io.Serializable

data class ExamResult (
    val number : Int?,
    val answer : String?,
    var isSelected : Boolean = false,
    var isAnswer : Boolean? = false,
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_exam_result

}