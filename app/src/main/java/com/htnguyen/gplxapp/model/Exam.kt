package com.htnguyen.gplxapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.base.adapter.BaseItem
import java.io.Serializable

@Entity(tableName = "Exam")
data class Exam(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String?,
    @SerializedName("content") val content : String?,
    @SerializedName("time") val time : Long?,
    @SerializedName("completeExam") val completeExam : Int?,
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_do_exam

}