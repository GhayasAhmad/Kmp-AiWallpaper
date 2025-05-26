package org.example.project.compose.multi.platform.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import composemultiplatform.composeapp.generated.resources.Res
import composemultiplatform.composeapp.generated.resources.ic_wallpapers
import org.jetbrains.compose.resources.painterResource

class SplashScreen() : Screen {

    @Composable
    override fun Content() {
        ScreenContent()
    }

    @Composable
    fun ScreenContent () {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Icon(
                    painter = painterResource(Res.drawable.ic_wallpapers),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ai Wallpapers",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    text = "Discover wallpapers that adapt, inspire and evolve powered by AI design.",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        lineHeight = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth(0.7F)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 40.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IndeterminateCircularIndicator()
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Please wait while data is loading...",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        }
    }


    @Composable
    fun IndeterminateCircularIndicator() {
        CircularProgressIndicator(
            modifier = Modifier.size(16.dp),
            color = MaterialTheme.colorScheme.onBackground,
            trackColor = MaterialTheme.colorScheme.background,
            strokeWidth = 2.dp
        )
    }

}