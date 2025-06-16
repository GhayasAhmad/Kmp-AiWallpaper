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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FeatureCardPreview() {
    FeatureCard()
}

@Composable
fun FeatureCard() {
    val imagesUrls = listOf(
        "https://images.pexels.com/photos/26797335/pexels-photo-26797335.jpeg",
        "https://images.pexels.com/photos/26797335/pexels-photo-26797335.jpeg",
        "https://images.pexels.com/photos/26797335/pexels-photo-26797335.jpeg",
        "https://images.pexels.com/photos/26797335/pexels-photo-26797335.jpeg",
        "https://images.pexels.com/photos/26797335/pexels-photo-26797335.jpeg",
        "https://images.pexels.com/photos/26797335/pexels-photo-26797335.jpeg",
        "https://images.pexels.com/photos/26797335/pexels-photo-26797335.jpeg"
    )

    Column {
        Text(
            text = "Featured",
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(imagesUrls) { index, imagesUrl ->
                val startPadding = if (index == 0) 16.dp else 0.dp
                FeaturedItem(
                    url = imagesUrl,
                    modifier = Modifier.padding(start = startPadding)
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
    Box(
        modifier = modifier
            .width(300.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = url,
            contentDescription = "Example Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
