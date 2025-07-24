package org.example.project.compose.multi.platform.di

import io.ktor.client.HttpClient
import org.example.project.compose.multi.platform.createHttpClient
import org.example.project.compose.multi.platform.data.api.PexelsApiService
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        createHttpClient()
    }
    single { PexelsApiService(get()) }
}