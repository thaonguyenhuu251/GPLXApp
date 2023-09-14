package com.htnguyen.gplxapp.model

import com.google.gson.annotations.SerializedName
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseItem
import java.io.Serializable

data class ExamDetail (
    @SerializedName("type") val type : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("id_exam") val id_exam : Int,
    @SerializedName("ask") val ask : String?,
    @SerializedName("answer_list") val answer_list : List<String>?,
    @SerializedName("urlImage") val urlImage : String?,
    @SerializedName("result") val result : String?,
    @SerializedName("complain") val complain : String?,
    var positionChoose : Int? = -1,
    var isCorrectResult : Boolean? = false,
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_exam_detail

}