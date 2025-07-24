package org.example.project.compose.multi.platform.ui.screens.imagePreview

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil3.compose.AsyncImage
import org.example.project.compose.multi.platform.domain.models.Photo

class FullScreenImagePreviewScreen(
    val photo: Photo
) : Screen {

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        BackHandler(true) {
            navigator?.pop()
        }

        FullScreenImagePreviewScreenContent(
            photo = photo,
            onButtonClick = {

            }
        )
    }

}

@Composable
fun FullScreenImagePreviewScreenContent(
    photo: Photo,
    onButtonClick: () -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var rotationState by remember { mutableStateOf(0f) }

    val transformableState = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale *= zoomChange
        offset += panChange
        rotationState += rotationChange
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            scale = 1f
                            offset = Offset.Zero
                            rotationState = 0f
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, rotation ->
                        scale *= zoom
                        offset += pan
                        rotationState += rotation
                    }
                }
                .transformable(transformableState)
        ) {
            AsyncImage(
                model = photo.src.original,
                contentDescription = photo.alt,
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y,
                        rotationZ = rotationState
                    )
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )
        }

        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Download")
        }
    }
}
