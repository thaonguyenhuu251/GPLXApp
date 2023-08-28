package com.htnguyen.gplxapp.view.base.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.htnguyen.gplxapp.model.Tips
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

fun readJSONFromAsset(context: Context, fileName: String) : String {
    var json: String? = null
    val  inputStream:InputStream = context.assets.open(fileName)
    json = inputStream.bufferedReader().use{it.readText()}
    return json
}


fun parseJsonToTrafficSigns(json: String) {
    Gson().fromJson(json, TrafficSigns::class.java)
}

fun parseJsonToListTrafficSigns(json: String) : Array<TrafficSigns> {
    val gson = Gson()
    val arrayTutorialType = object : TypeToken<Array<TrafficSigns>>() {}.type
    val tutorials: Array<TrafficSigns> = gson.fromJson(json, arrayTutorialType)
    return tutorials
}

fun parseJsonToListTips(json: String) : Array<Tips> {
    val gson = Gson()
    val arrayTutorialType = object : TypeToken<Array<Tips>>() {}.type
    val tutorials: Array<Tips> = gson.fromJson(json, arrayTutorialType)
    return tutorials
}

fun ImageView.loadImageUrl(url: String?) {
    print(url)
    url?.let {
        Glide.with(this.context)
            .load(url)
            .into(this)

    }
}