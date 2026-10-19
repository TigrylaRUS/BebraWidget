package com.example.bebrascreens.presentation.albums

sealed interface AlbumsIntent {
    data object LoadAlbums: AlbumsIntent
    data class SelectedAlbum(val albumId: String): AlbumsIntent
}