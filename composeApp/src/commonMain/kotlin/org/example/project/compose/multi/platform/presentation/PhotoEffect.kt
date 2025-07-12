package org.example.project.compose.multi.platform.presentation

sealed class PhotoEffect {
    data class ShowError(val message: String) : PhotoEffect()
    data class ShowToast(val message: String) : PhotoEffect()
}