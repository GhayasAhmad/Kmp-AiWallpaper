package org.example.project.compose.multi.platform.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PexelsResponse(
    val page: Int,
    @SerialName("per_page") val perPage: Int,
    val photos: List<Photo>,
    @SerialName("total_results") val totalResults: Int,
    @SerialName("next_page") val nextPage: String?
)