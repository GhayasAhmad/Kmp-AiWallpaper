package org.example.project.compose.multi.platform.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.compose.multi.platform.domain.usecases.GetCuratedPhotosUseCase
import org.example.project.compose.multi.platform.domain.usecases.SearchPhotosUseCase
import org.example.project.compose.multi.platform.presentation.intent.PhotoIntent
import org.example.project.compose.multi.platform.presentation.state.PhotoViewState

class PhotoViewModel(
    private val getCuratedPhotosUseCase: GetCuratedPhotosUseCase,
    private val searchPhotosUseCase: SearchPhotosUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<PhotoViewState>(PhotoViewState.NotStarted)
    val viewState = _viewState.asStateFlow()

    private val _effects = MutableSharedFlow<PhotoEffect>()
    val effects = _effects.asSharedFlow()

    fun handleIntent(intent: PhotoIntent) {
        when (intent) {
            is PhotoIntent.LoadCuratedPhotos -> loadCuratedPhotos()
            is PhotoIntent.SearchPhotos -> searchPhotos(intent.query)
            is PhotoIntent.LoadMorePhotos -> loadMorePhotos()
            is PhotoIntent.RetryLoading -> retryLoading()
        }
    }

    private fun loadCuratedPhotos() {
        viewModelScope.launch {
            _viewState.value = PhotoViewState.Loading

            getCuratedPhotosUseCase(page = 1).fold(
                onSuccess = { response ->
                    _viewState.value = PhotoViewState.Success(
                        photos = response.photos,
                        currentPage = response.page,
                        hasMorePages = response.nextPage != null,
                        searchQuery = ""
                    )
                },
                onFailure = { exception ->
                    _viewState.value = PhotoViewState.Error(
                        message = exception.message ?: "Unknown error",
                        photos = emptyList(),
                        currentPage = 1,
                        hasMorePages = true,
                        searchQuery = ""
                    )
                    _effects.emit(PhotoEffect.ShowError(exception.message ?: "Unknown error"))
                }
            )
        }
    }

    private fun searchPhotos(query: String) {
        if (query.isBlank()) {
            loadCuratedPhotos()
            return
        }

        viewModelScope.launch {
            _viewState.value = PhotoViewState.Loading

            searchPhotosUseCase(query, page = 1).fold(
                onSuccess = { response ->
                    _viewState.value = PhotoViewState.Success(
                        photos = response.photos,
                        currentPage = response.page,
                        hasMorePages = response.nextPage != null,
                        searchQuery = query
                    )
                },
                onFailure = { exception ->
                    _viewState.value = PhotoViewState.Error(
                        message = exception.message ?: "Search failed",
                        photos = emptyList(),
                        currentPage = 1,
                        hasMorePages = true,
                        searchQuery = query
                    )
                    _effects.emit(PhotoEffect.ShowError(exception.message ?: "Search failed"))
                }
            )
        }
    }

    private fun loadMorePhotos() {
        val currentState = _viewState.value

        val (currentPhotos, currentPage, hasMorePages, searchQuery) = when (currentState) {
            is PhotoViewState.Success -> {
                Tuple4(
                    currentState.photos,
                    currentState.currentPage,
                    currentState.hasMorePages,
                    currentState.searchQuery
                )
            }

            is PhotoViewState.LoadingMore -> {
                Tuple4(
                    currentState.photos,
                    currentState.currentPage,
                    currentState.hasMorePages,
                    currentState.searchQuery
                )
            }

            is PhotoViewState.Error -> {
                Tuple4(
                    currentState.photos,
                    currentState.currentPage,
                    currentState.hasMorePages,
                    currentState.searchQuery
                )
            }

            else -> return
        }

        if (!hasMorePages) return

        viewModelScope.launch {
            _viewState.value = PhotoViewState.LoadingMore(
                photos = currentPhotos,
                currentPage = currentPage,
                hasMorePages = hasMorePages,
                searchQuery = searchQuery
            )

            val nextPage = currentPage + 1
            val result = if (searchQuery.isNotBlank()) {
                searchPhotosUseCase(searchQuery, nextPage)
            } else {
                getCuratedPhotosUseCase(nextPage)
            }

            result.fold(
                onSuccess = { response ->
                    _viewState.value = PhotoViewState.Success(
                        photos = currentPhotos + response.photos,
                        currentPage = response.page,
                        hasMorePages = response.nextPage != null,
                        searchQuery = searchQuery
                    )
                },
                onFailure = { exception ->
                    _viewState.value = PhotoViewState.Error(
                        message = "Failed to load more photos",
                        photos = currentPhotos,
                        currentPage = currentPage,
                        hasMorePages = hasMorePages,
                        searchQuery = searchQuery
                    )
                    _effects.emit(PhotoEffect.ShowError("Failed to load more photos"))
                }
            )
        }
    }

    private fun retryLoading() {
        val currentState = _viewState.value
        val searchQuery = when (currentState) {
            is PhotoViewState.Success -> currentState.searchQuery
            is PhotoViewState.LoadingMore -> currentState.searchQuery
            is PhotoViewState.Error -> currentState.searchQuery
            else -> ""
        }

        if (searchQuery.isNotBlank()) {
            searchPhotos(searchQuery)
        } else {
            loadCuratedPhotos()
        }
    }
}

private data class Tuple4<A, B, C, D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
)