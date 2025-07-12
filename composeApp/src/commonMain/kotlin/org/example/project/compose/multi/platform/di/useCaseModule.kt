package org.example.project.compose.multi.platform.di

import org.example.project.compose.multi.platform.domain.usecases.GetCuratedPhotosUseCase
import org.example.project.compose.multi.platform.domain.usecases.SearchPhotosUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCuratedPhotosUseCase(get()) }
    single { SearchPhotosUseCase(get()) }
}