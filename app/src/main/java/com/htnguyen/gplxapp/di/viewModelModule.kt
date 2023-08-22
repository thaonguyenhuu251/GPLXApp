package com.htnguyen.gplxapp.di

import com.htnguyen.gplxapp.view.fragment.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
}

