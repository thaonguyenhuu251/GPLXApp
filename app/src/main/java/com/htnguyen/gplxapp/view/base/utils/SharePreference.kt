package com.htnguyen.gplxapp.view.base.utils

import android.content.Context
import android.content.SharedPreferences
import com.htnguyen.gplxapp.MainApp
import com.htnguyen.gplxapp.R

object SharePreference {
    private val pref = MainApp.instance.getSharedPreferences(
        MainApp.instance.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )
    private val preferencesEdit = pref.edit()

    const val PREF_FONT_TEXT = "PREF_FONT_TEXT"
    const val PREF_LOGIN_FIRST = "PREF_LOGIN_FIRST"
    const val PREF_LIENCE = "PREF_LIENCE"
    const val PREF_MODE = "PREF_MODE"
    const val PREF_TIME_NOTIFICATION = "PREF_TIME_NOTIFICATION"
    const val PREF_NOTIFICATION = "PREF_NOTIFICATION"
    const val PREF_ANSWER_NOTIFICATION = "PREF_ANSWER_NOTIFICATION"
    const val PREF_COUNT_ANSWER_NOTIFICATION = "PREF_COUNT_ANSWER_NOTIFICATION"

    var fontText: Int
        get() {
            return pref.getInt(PREF_FONT_TEXT, 4)
        }
        set(value) {
            preferencesEdit.putInt(PREF_FONT_TEXT, value).apply()
        }

    var isLoginFirst: Boolean
        get() {
            return pref.getBoolean(PREF_LOGIN_FIRST, true)
        }
        set(value) {
            preferencesEdit.putBoolean(PREF_LOGIN_FIRST, value).apply()
        }

    var lience: String?
        get() {
            return pref.getString(PREF_LIENCE, "A1")
        }
        set(value) {
            preferencesEdit.putString(PREF_LIENCE, value).apply()
        }

    var isModeLight: Boolean
        get() {
            return pref.getBoolean(PREF_MODE, true)
        }
        set(value) {
            preferencesEdit.putBoolean(PREF_MODE, value).apply()
        }

    var timeNotification: String?
        get() {
            return pref.getString(PREF_TIME_NOTIFICATION, "10:00")
        }
        set(value) {
            preferencesEdit.putString(PREF_TIME_NOTIFICATION, value).apply()
        }

    var isNotification: Boolean
        get() {
            return pref.getBoolean(PREF_NOTIFICATION, true)
        }
        set(value) {
            preferencesEdit.putBoolean(PREF_NOTIFICATION, value).apply()
        }


    var isAnswerNotification: Boolean
        get() {
            return pref.getBoolean(PREF_ANSWER_NOTIFICATION, true)
        }
        set(value) {
            preferencesEdit.putBoolean(PREF_ANSWER_NOTIFICATION, value).apply()
        }

    var countAnswerNotification: Int
        get() {
            return pref.getInt(PREF_COUNT_ANSWER_NOTIFICATION, 0)
        }
        set(value) {
            preferencesEdit.putInt(PREF_COUNT_ANSWER_NOTIFICATION, value).apply()
        }
}
