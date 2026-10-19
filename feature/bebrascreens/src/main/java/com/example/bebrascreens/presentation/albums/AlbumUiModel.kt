package com.example.bebrascreens.presentation.albums

import androidx.compose.ui.graphics.Color
import com.example.domain.model.Album

data class AlbumUiModel(
    val album: Album,
    val isSelected: Boolean = false,
    val backgroundColor: Color = Color(0xFF73B4C8)
)