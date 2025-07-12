package org.example.project.compose.multi.platform.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class PhotoSrc(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)