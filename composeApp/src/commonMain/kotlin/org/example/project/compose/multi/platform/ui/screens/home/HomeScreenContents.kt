package org.example.project.compose.multi.platform.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import org.example.project.compose.multi.platform.presentation.PhotoViewModel
import org.example.project.compose.multi.platform.presentation.intent.PhotoIntent
import org.example.project.compose.multi.platform.presentation.state.PhotoViewState
import org.example.project.compose.multi.platform.ui.screens.home.components.FeatureCard
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun HomeScreenContentPreview() {
    HomeScreenContent(
        onBackClick = {}
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenContent(
    viewModel: PhotoViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {

    val state by viewModel.viewState.collectAsState()

    when (state) {
        is PhotoViewState.Error -> {
            Logger.d("Error occurred ${(state as PhotoViewState.Error).message})")
        }

        PhotoViewState.Loading -> {
            print("Loading")
        }

        is PhotoViewState.LoadingMore -> { ->
            print("Loading more ${(state as PhotoViewState.LoadingMore).photos}")
        }

        PhotoViewState.NotStarted -> {
            print("Not started")
        }

        is PhotoViewState.Success -> {
            print("Success ${(state as PhotoViewState.Success).photos}")
        }
    }

    viewModel.handleIntent(PhotoIntent.LoadCuratedPhotos)


    BackHandler(true) {
        onBackClick.invoke()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.padding(top = 8.dp))
        FeatureCard()
    }
}

