package com.htnguyen.gplxapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseItem
import java.io.Serializable

@Entity(tableName = "StatusExam")
data class StatusExam(
    @PrimaryKey
    @SerializedName("idAsk") val idAsk : Int,
    @SerializedName("idExam") val idExam : Int,
    @SerializedName("idType") val idType: Int,
    @SerializedName("statusAsk") val statusAsk : Int?,
    var isSelected : Boolean = false
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_result_all_exam
}
