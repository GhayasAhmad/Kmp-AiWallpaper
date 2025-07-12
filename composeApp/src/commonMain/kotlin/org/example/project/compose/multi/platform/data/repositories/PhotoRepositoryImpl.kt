package org.example.project.compose.multi.platform.data.repositories

import org.example.project.compose.multi.platform.data.api.PexelsApiService
import org.example.project.compose.multi.platform.domain.models.PexelsResponse
import org.example.project.compose.multi.platform.domain.repositories.PhotoRepository

class PhotoRepositoryImpl(
    private val apiService: PexelsApiService
) : PhotoRepository {

    override suspend fun getCuratedPhotos(page: Int, perPage: Int): Result<PexelsResponse> {
        return try {
            val response = apiService.getCuratedPhotos(page, perPage)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): Result<PexelsResponse> {
        return try {
            val response = apiService.searchPhotos(query, page, perPage)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}