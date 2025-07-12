package org.example.project.compose.multi.platform.di

import org.example.project.compose.multi.platform.data.repositories.PhotoRepositoryImpl
import org.example.project.compose.multi.platform.domain.repositories.PhotoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PhotoRepository> { PhotoRepositoryImpl(get()) }
}