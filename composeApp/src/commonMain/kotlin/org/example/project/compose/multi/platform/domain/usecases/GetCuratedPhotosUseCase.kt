package org.example.project.compose.multi.platform.domain.usecases

import org.example.project.compose.multi.platform.domain.models.PexelsResponse
import org.example.project.compose.multi.platform.domain.repositories.PhotoRepository

class GetCuratedPhotosUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        perPage: Int = 80
    ): Result<PexelsResponse> {
        return repository.getCuratedPhotos(page, perPage)
    }
}