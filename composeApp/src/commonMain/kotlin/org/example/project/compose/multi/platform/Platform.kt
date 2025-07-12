package org.example.project.compose.multi.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initKoin()