package org.example.project.compose.multi.platform

import io.ktor.client.HttpClient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initKoin()

expect fun createHttpClient(): HttpClient