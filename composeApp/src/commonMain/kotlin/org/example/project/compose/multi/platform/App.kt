package org.example.project.compose.multi.platform

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.compose.multi.platform.ui.screens.splash.SplashScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
//        typography = AppTypography
    ) {
        Navigator(SplashScreen())
    }
}