package com.htnguyen.gplxapp.model

import com.google.gson.annotations.SerializedName
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseItem
import java.io.Serializable

data class TrafficsLearnDetail (
    @SerializedName("type") val type : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("ask") val ask : String?,
    @SerializedName("answer_list") val answer_list : List<String>?,
    @SerializedName("urlImage") val urlImage : String?,
    @SerializedName("result") val result : String?,
    @SerializedName("complain") val complain : String?,
    var answerSelected : Int? = -1,
    var isAnswer : Boolean? = false,
    var isSelected : Boolean? = false
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_trafic_learn_detail

}