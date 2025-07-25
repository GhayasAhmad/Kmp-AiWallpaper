package org.example.project.compose.multi.platform

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initKoin()

expect fun createHttpClient(): HttpClient
expect fun createHttpClientEngine(): HttpClientEngine