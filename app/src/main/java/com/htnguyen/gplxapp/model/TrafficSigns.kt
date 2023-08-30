package com.htnguyen.gplxapp.model

import com.google.gson.annotations.SerializedName
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseItem
import java.io.Serializable

data class TrafficSigns (
    @SerializedName("type") val type : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String?,
    @SerializedName("content") val content : String?,
    @SerializedName("urlImage") val urlImage : String?,
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_turn_forbidden

}