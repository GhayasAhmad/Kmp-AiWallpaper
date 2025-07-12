package org.example.project.compose.multi.platform.di

import org.example.project.compose.multi.platform.presentation.PhotoViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { PhotoViewModel(get(), get()) }
}