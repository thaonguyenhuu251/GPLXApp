package com.htnguyen.gplxapp.model

import com.google.gson.annotations.SerializedName
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.view.base.adapter.BaseItem
import java.io.Serializable

data class Tips (
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String?,
    @SerializedName("content") val content : String?,
    var isSelected: Boolean = false,
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_tips

}