package org.example.project.compose.multi.platform.ui.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.project.compose.multi.platform.ui.screens.exit.ExitScreen

class HomeScreen() : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        HomeScreenContent(
            onBackClick = {
                navigator?.push(ExitScreen())
            }
        )
    }

}