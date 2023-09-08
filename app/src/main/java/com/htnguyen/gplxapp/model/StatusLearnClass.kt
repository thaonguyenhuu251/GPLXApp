package com.htnguyen.gplxapp.model

import com.google.gson.annotations.SerializedName
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseItem
import java.io.Serializable

data class StatusLearnClass(
    @SerializedName("idAsk") val idAsk : Int,
    @SerializedName("statusAsk") val statusAsk : Int?,
) : Serializable {

}
