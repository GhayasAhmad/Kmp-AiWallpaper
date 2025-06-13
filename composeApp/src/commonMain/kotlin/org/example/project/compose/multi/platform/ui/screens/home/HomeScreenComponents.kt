package org.example.project.compose.multi.platform.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composemultiplatform.composeapp.generated.resources.Res
import composemultiplatform.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun HomeScreenContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        TopBarHomeScreen()
    }
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
            .padding(16.dp)
    ) {
        TopBarHomeScreen(
            onBackClick = onBackClick
        )
    }
}


@Composable
fun TopBarHomeScreen(
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(16.dp)
    ) {
//        Icon(
//            painter = painterResource(Res.drawable.ic_back),
//            contentDescription = "Back",
//            modifier = Modifier
//                .align(Alignment.CenterStart)
//                .clickable {
//                    onBackClick.invoke()
//                    println("DEBUG: BackPress Click")
//                }
//        )

        Text(
            text = "Wallpapers",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}