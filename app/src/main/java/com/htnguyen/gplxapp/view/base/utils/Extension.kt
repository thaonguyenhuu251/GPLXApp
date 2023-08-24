package com.htnguyen.gplxapp.view.base.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.htnguyen.gplxapp.model.TrafficSigns
import java.io.*

fun loadJSONFromAsset(context: Context): String {
    val inputStream: InputStream = context.assets.open("traffigs_signs.json")
    val size: Int = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    val jsonString = String(buffer)

    println(jsonString)

    return jsonString
}

fun readJSONFromAsset(context: Context, fileName: String): String? {
    var json: String? = null
    try {
        val  inputStream:InputStream = context.assets.open(fileName)
        json = inputStream.bufferedReader().use{it.readText()}
    } catch (ex: Exception) {
        ex.printStackTrace()
        return null
    }
    return json
}

fun parseJsonToTrafficSigns(json: String) {
    Gson().fromJson(json, TrafficSigns::class.java)
}

fun parseJsonToListTrafficSigns(json: String) : List<TrafficSigns> {
    val listCountryType = object : TypeToken<List<TrafficSigns>>() {}.type
    return Gson().fromJson(json, listCountryType)
}