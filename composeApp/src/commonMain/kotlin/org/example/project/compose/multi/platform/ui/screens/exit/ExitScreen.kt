package org.example.project.compose.multi.platform.ui.screens.exit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil3.compose.AsyncImage
import composemultiplatformaiwallpaper.composeapp.generated.resources.Res
import composemultiplatformaiwallpaper.composeapp.generated.resources.ic_rate_us
import org.jetbrains.compose.resources.painterResource

class ExitScreen() : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        ExitScreenContent(
            onBackClick = {
                navigator?.pop()
            },
            onExitClick = {

            }
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExitScreenContent(
    onBackClick: () -> Unit,
    onExitClick: () -> Unit
) {

    androidx.compose.ui.backhandler.BackHandler(true) {
        onBackClick.invoke()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = Res.getUri("drawable/exit_image.png"),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "See you soon!",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Thank you for using Ai Wallpaper. Hope to see you soon.",
                color = Color(0xFF666666),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp),

                )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    onBackClick.invoke()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF007AFF)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Stay Here",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    onExitClick.invoke()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF5F5F5)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Exit App",
                    color = Color(0xFFFF3B30),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.clickable(
                    onClick = {
                        // Do rate us thing here.
                    }
                )
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_rate_us),
                    contentDescription = "Star",
                    tint = Color(0xFF007AFF),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Rate us on App Store",
                    color = Color(0xFF007AFF),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}