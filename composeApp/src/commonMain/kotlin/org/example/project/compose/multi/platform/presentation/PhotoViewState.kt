package org.example.project.compose.multi.platform.presentation

import org.example.project.compose.multi.platform.domain.models.Photo

data class PhotoViewState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String? = null,
    val isLoadingMore: Boolean = false,
    val currentPage: Int = 1,
    val hasMorePages: Boolean = true,
    val searchQuery: String = ""
)