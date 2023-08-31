package com.htnguyen.gplxapp.model

import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseItem
import java.io.Serializable

data class TrafficsLearnResult (
    val number : Int?,
    val answer : String?,
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_trafic_learn_result

}