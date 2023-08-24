package com.htnguyen.gplxapp.view.base.adapter

import com.htnguyen.gplxapp.R

class LoadingItem : BaseItem() {
    override val layoutResourceId: Int
        get() = R.layout.item_loading
    override var dataType: Int = 1

}