package org.example.project.compose.multi.platform.di

import org.example.project.compose.multi.platform.presentation.PhotoScreenModel
import org.koin.dsl.module

val viewModelModule = module {
//    viewModel { PhotoViewModel(get(), get()) }
    factory { PhotoScreenModel(get(), get()) }
}