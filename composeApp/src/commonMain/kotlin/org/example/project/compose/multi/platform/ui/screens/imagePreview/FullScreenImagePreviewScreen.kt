package org.example.project.compose.multi.platform.ui.screens.imagePreview

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

class FullScreenImagePreviewScreen() : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
    }

}