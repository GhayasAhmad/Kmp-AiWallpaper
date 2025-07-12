package org.example.project.compose.multi.platform.presentation.intent

sealed class PhotoIntent {
    object LoadCuratedPhotos : PhotoIntent()
    data class SearchPhotos(val query: String) : PhotoIntent()
    object LoadMorePhotos : PhotoIntent()
    object RetryLoading : PhotoIntent()
}