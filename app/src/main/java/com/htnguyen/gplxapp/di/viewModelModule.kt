package com.htnguyen.gplxapp.di

import com.htnguyen.gplxapp.view.fragment.home.HomeViewModel
import com.htnguyen.gplxapp.view.fragment.learning.ExamViewModel
import com.htnguyen.gplxapp.view.fragment.learning.LearningViewModel
import com.htnguyen.gplxapp.viewModels.LearningDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { LearningViewModel(get()) }
    viewModel { ExamViewModel(get()) }
    viewModel { LearningDetailViewModel(get()) }
}

