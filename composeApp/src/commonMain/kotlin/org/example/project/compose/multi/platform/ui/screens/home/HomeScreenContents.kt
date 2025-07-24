package org.example.project.compose.multi.platform.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.compose.multi.platform.presentation.PhotoScreenModel
import org.example.project.compose.multi.platform.presentation.intent.PhotoIntent
import org.example.project.compose.multi.platform.presentation.state.PhotoViewState
import org.example.project.compose.multi.platform.ui.screens.home.components.FeatureCard
import org.example.project.compose.multi.platform.ui.screens.home.components.PhotoListItemWithAspectRatio

//@OptIn(ExperimentalComposeUiApi::class)
//@Preview
//@Composable
//fun HomeScreenContentPreview() {
//    HomeScreenContent(
//        onBackClick = {}
//    )
//}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenContent(
    viewModel: PhotoScreenModel,
    onBackClick: () -> Unit
) {
    val hasFetched = rememberSaveable { mutableStateOf(false) }

    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        if (!hasFetched.value) {
            hasFetched.value = true
            viewModel.handleIntent(PhotoIntent.LoadCuratedPhotos)
        }
    }

    BackHandler(true) {
        onBackClick()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        when (val currentState = state) {
            is PhotoViewState.Success -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing = 8.dp
                ) {
                    item(span = StaggeredGridItemSpan.FullLine) {
                        FeatureCard(currentState)
                    }

                    item(span = StaggeredGridItemSpan.FullLine) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item(span = StaggeredGridItemSpan.FullLine) {
                        Text(
                            text = "Ai Wallpapers",
                            modifier = Modifier
                                .padding(start = 16.dp),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    items(currentState.photos) { photo ->
                        PhotoListItemWithAspectRatio(
                            photo = photo
                        )
                    }
                }
            }

            is PhotoViewState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is PhotoViewState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: ${currentState.message}")
                }
            }

            PhotoViewState.NotStarted -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Waiting to load...")
                }
            }

            is PhotoViewState.LoadingMore -> {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing = 8.dp
                ) {
                    item(span = StaggeredGridItemSpan.FullLine) {
                        FeatureCard(currentState)
                    }

                    item(span = StaggeredGridItemSpan.FullLine) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(currentState.photos) { photo ->
                        PhotoListItemWithAspectRatio(photo = photo)
                    }
                }
            }
        }
    }
}


