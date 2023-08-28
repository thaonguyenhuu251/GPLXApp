package com.htnguyen.gplxapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.htnguyen.gplxapp.R
import com.htnguyen.gplxapp.view.base.adapter.BaseItem
import java.io.Serializable

@Entity(tableName = "TrafficsLearn")
data class TrafficsLearn (
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String?,
    @SerializedName("content") val content : String?,
    @SerializedName("urlImage") val urlImage : String?,
    @SerializedName("allLesson") val allLesson : Int?,
    @SerializedName("completeLesson") val completeLesson : Int?,
) : BaseItem(), Serializable {
    override val layoutResourceId: Int
        get() = R.layout.item_trafic_learn

}