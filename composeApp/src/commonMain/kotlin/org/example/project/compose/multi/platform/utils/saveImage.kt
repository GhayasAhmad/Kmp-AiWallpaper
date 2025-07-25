package org.example.project.compose.multi.platform.utils

import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.saveImageToGallery
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsBytes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import org.example.project.compose.multi.platform.createHttpClientEngine
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
suspend fun FileKit.saveImage(url: String) {
    val bytes = CoroutineScope(Dispatchers.IO).async {
        HttpClient(createHttpClientEngine()).get(url).bodyAsBytes()
    }.await()
//    val bytes = CoroutineScope(Dispatchers.IO).async {
//        client.get(url).bodyAsBytes()
//    }.await()
    saveImageToGallery(
        bytes = bytes,
        filename = "test.jpeg",
    )
}