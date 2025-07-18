package org.example.project.compose.multi.platform.presentation.state

import org.example.project.compose.multi.platform.domain.models.Photo

sealed interface PhotoViewState {
    data object NotStarted : PhotoViewState

    data object Loading : PhotoViewState

    data class Success(
        val photos: List<Photo>,
        val currentPage: Int,
        val hasMorePages: Boolean,
        val searchQuery: String
    ) : PhotoViewState

    data class LoadingMore(
        val photos: List<Photo>,
        val currentPage: Int,
        val hasMorePages: Boolean,
        val searchQuery: String
    ) : PhotoViewState

    data class Error(
        val message: String,
        val photos: List<Photo> = emptyList(),
        val currentPage: Int = 1,
        val hasMorePages: Boolean = true,
        val searchQuery: String = ""
    ) : PhotoViewState
}