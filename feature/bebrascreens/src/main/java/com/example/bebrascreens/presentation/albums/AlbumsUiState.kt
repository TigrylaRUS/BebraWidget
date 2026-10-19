package com.example.bebrascreens.presentation.albums

import com.example.domain.model.Album

data class AlbumsUiState(
    val isLoading: Boolean = false,
    val albumSelected: Album? = null,
    val albumUiModel: List<AlbumUiModel> = emptyList(),
    val error: String? = null,
    val isEmpty: Boolean = false
)
