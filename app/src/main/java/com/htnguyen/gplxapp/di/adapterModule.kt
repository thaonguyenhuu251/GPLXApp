package com.htnguyen.gplxapp.di

import com.htnguyen.gplxapp.base.adapter.BaseItem
import com.htnguyen.gplxapp.base.adapter.BaseRecyclerViewAdapter
import org.koin.dsl.module

val adapterModule = module {
    single { BaseRecyclerViewAdapter<BaseItem>() }
}