package org.example.project.compose.multi.platform.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.project.compose.multi.platform.presentation.PhotoScreenModel
import org.example.project.compose.multi.platform.presentation.intent.PhotoIntent
import org.example.project.compose.multi.platform.ui.screens.exit.ExitScreen

class HomeScreen() : Screen {


    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel: PhotoScreenModel = koinScreenModel()
        val hasFetched = rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            if (!hasFetched.value) {
                hasFetched.value = true
                viewModel.handleIntent(PhotoIntent.LoadCuratedPhotos)
            }
        }

        val state by viewModel.viewState.collectAsState()

        HomeScreenContent(
            state = state,
            onBackClick = {
                navigator?.push(ExitScreen())
            }
        )
    }

}