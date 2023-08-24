package com.htnguyen.gplxapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrafficSigns (
    @SerializedName("id") @Expose val id : Int,
    @SerializedName("name") @Expose val name : String?,
    @SerializedName("content") @Expose val content : String?,
    @SerializedName("urlImage") @Expose val urlImage : Int?,
)