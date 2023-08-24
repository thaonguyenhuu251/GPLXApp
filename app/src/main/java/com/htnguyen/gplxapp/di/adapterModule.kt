package com.htnguyen.gplxapp.di

import com.htnguyen.gplxapp.view.base.adapter.BaseItem
import com.htnguyen.gplxapp.view.base.adapter.BaseRecyclerViewAdapter
import org.koin.dsl.module

val adapterModule = module {
    single { BaseRecyclerViewAdapter<BaseItem>() }
}