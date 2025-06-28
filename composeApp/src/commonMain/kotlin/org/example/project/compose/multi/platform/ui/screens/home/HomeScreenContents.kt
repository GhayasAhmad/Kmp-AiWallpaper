package org.example.project.compose.multi.platform.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.compose.multi.platform.ui.screens.home.components.FeatureCard
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun HomeScreenContentPreview() {
    HomeScreenContent{}
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenContent(
    onBackClick: () -> Unit
) {
    androidx.compose.ui.backhandler.BackHandler(true) {
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

