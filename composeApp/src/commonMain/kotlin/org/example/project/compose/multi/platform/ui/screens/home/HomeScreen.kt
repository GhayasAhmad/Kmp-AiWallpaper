package org.example.project.compose.multi.platform.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.project.compose.multi.platform.presentation.PhotoViewModel
import org.example.project.compose.multi.platform.ui.screens.exit.ExitScreen
import org.koin.mp.KoinPlatform.getKoin

class HomeScreen() : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = remember { getKoin().get<PhotoViewModel>() }

        HomeScreenContent(
            onBackClick = {
                navigator?.push(ExitScreen())
            }
        )
    }

}