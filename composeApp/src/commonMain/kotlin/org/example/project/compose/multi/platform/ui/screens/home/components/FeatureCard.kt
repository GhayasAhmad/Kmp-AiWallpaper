package org.example.project.compose.multi.platform.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.compose.multi.platform.presentation.state.PhotoViewState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FeatureCardPreview() {
    FeatureCard()
}

@Composable
fun FeatureCard(
    state: PhotoViewState? = null
) {
    val featuredPhotos = (state as? PhotoViewState.Success)?.featuredPhotos
    if (featuredPhotos.isNullOrEmpty()) return

    Column {
        Text(
            text = "Featured",
            modifier = Modifier
                .padding(start = 16.dp),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(
                items = featuredPhotos
            ) { index, photo ->
                val startPadding = if (index == 0) 16.dp else 0.dp
                val endPadding = if (index == featuredPhotos.lastIndex) 16.dp else 0.dp
                FeaturedItem(
                    url = photo.src.landscape,
                    modifier = Modifier.padding(start = startPadding, end = endPadding)
                )
            }
        }
    }
}


@Composable
fun FeaturedItem(
    url: String,
    modifier: Modifier
) {
    var isImageLoading by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .width(300.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        if (isImageLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shimmerEffect()
            )
        }

        AsyncImage(
            model = url,
            contentDescription = "Featured Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            onSuccess = {
                isImageLoading = false
            },
            onError = {
                println("iOS Image Error message: ${it.result.throwable.message}")
                isImageLoading = false
            }
        )
    }
}
