package org.example.project.compose.multi.platform

import org.example.project.compose.multi.platform.di.appModule
import org.koin.core.context.startKoin

fun initKoinCommon() {
    startKoin {
        modules(appModule)
    }
}