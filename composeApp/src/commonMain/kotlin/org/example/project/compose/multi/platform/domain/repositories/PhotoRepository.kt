package org.example.project.compose.multi.platform.domain.repositories

import org.example.project.compose.multi.platform.domain.models.PexelsResponse

interface PhotoRepository {
    suspend fun getCuratedPhotos(page: Int, perPage: Int): Result<PexelsResponse>
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): Result<PexelsResponse>
}