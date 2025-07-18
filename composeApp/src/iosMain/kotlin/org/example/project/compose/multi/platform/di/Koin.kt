package org.example.project.compose.multi.platform.di

import org.koin.mp.KoinPlatform
import org.koin.sample.UserPresenter

fun getUserPresenter(): UserPresenter = KoinPlatform.getKoin().get()