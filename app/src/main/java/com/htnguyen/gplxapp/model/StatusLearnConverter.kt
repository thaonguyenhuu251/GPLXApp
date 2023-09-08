package com.htnguyen.gplxapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class StatusLearnConverter {
    @TypeConverter
    fun storedStringToStatusLearn(value: String): List<StatusLearn> {
        val gson = Gson()
        val listType: Type = object : TypeToken<List<StatusLearn>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun statusLearnToString(statusLearn: List<StatusLearn>): String {
        val gson = Gson()
        return gson.toJson(statusLearn)
    }
}