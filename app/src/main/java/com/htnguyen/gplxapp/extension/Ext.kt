package com.htnguyen.gplxapp.extension

import android.view.View

class Ext {

    fun View.visible() {
        visibility = View.VISIBLE
    }

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    fun View.begone() {
        visibility = View.GONE
    }
}