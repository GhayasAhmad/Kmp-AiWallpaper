package org.example.project.compose.multi.platform.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.compose.multi.platform.domain.models.Photo

@Composable
fun PhotoListItemWithAspectRatio(
    photo: Photo,
) {

    data class ImageState(
        val size: IntSize = IntSize.Zero,
        val isLoading: Boolean = true,
        val hasError: Boolean = false
    )

    var imageState by remember { mutableStateOf(ImageState()) }

    val aspectRatio = if (imageState.size != IntSize.Zero && !imageState.isLoading) {
        val ratio = imageState.size.width.toFloat() / imageState.size.height.toFloat()
        ratio.coerceIn(0.5f, 2.0f)
    } else {
        2f / 3f
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio)
                .clip(RoundedCornerShape(16.dp))
        ) {
            if (imageState.isLoading && !imageState.hasError) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                )
            }

            AsyncImage(
                model = photo.src.medium,
                contentDescription = photo.alt,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                onSuccess = { result ->
                    val intrinsicSize = result.painter.intrinsicSize
                    imageState = ImageState(
                        size = IntSize(
                            width = if (intrinsicSize.width.isFinite()) intrinsicSize.width.toInt() else 400,
                            height = if (intrinsicSize.height.isFinite()) intrinsicSize.height.toInt() else 600
                        ),
                        isLoading = false,
                        hasError = false
                    )
                },
                onError = {
                    imageState = imageState.copy(isLoading = false, hasError = true)
                }
            )

            if (imageState.hasError) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color(0xFFE0E0E0),
                            RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Failed to load",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}