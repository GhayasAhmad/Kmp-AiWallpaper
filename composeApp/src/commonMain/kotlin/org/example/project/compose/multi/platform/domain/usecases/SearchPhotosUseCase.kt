package org.example.project.compose.multi.platform.domain.usecases

import org.example.project.compose.multi.platform.domain.models.PexelsResponse
import org.example.project.compose.multi.platform.domain.repositories.PhotoRepository

class SearchPhotosUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        perPage: Int = 20
    ): Result<PexelsResponse> {
        return repository.searchPhotos(
            query,
            page,
            perPage
        )
    }
}