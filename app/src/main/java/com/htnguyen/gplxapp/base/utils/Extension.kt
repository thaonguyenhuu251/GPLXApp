package com.htnguyen.gplxapp.base.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Px
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.htnguyen.gplxapp.R
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

fun Context.showAlertDialog(
    message: String,
    positiveLabel: String,
    negativeLabel: String,
    onPositiveClickListener: () -> Unit = {},
    onNegativeClickListener: () -> Unit = {},
) {
    val builder = AlertDialog.Builder(this, R.style.FinishActionDialog)

    val mLayoutInflater = LayoutInflater.from(this)
    val mView: View = mLayoutInflater.inflate(R.layout.dialog_alert, null)
    val mTextView = mView.findViewById(R.id.message) as TextView
    mTextView.text = message

    val positiveButton = mView.findViewById<Button>(R.id.btn_positive)
    positiveButton.text = positiveLabel

    val negativeButton = mView.findViewById<Button>(R.id.btn_negative)
    negativeButton.text = negativeLabel

    builder.setCustomTitle(mView)

    val dialog = builder.show()
    positiveButton.setOnClickListener {
        dialog.dismiss()
        onPositiveClickListener()
    }


    negativeButton.setOnClickListener {
        dialog.dismiss()
        onNegativeClickListener()
    }
}

