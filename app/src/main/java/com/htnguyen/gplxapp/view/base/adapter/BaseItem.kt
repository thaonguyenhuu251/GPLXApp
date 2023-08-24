package com.htnguyen.gplxapp.view.base.adapter

import androidx.databinding.BaseObservable

abstract class BaseItem : BaseObservable() {
    private var objectTitle: String?

    get() = objectTitle
    set(value) {
        objectTitle = value
    }

    abstract val layoutResourceId: Int

    open var itemKey: Any? = null
    open var dataType: Int = 0

}