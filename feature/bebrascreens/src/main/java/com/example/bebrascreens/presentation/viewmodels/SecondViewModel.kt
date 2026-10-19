package com.example.bebrascreens.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bebrascreens.presentation.albums.AlbumUiModel
import com.example.bebrascreens.presentation.albums.AlbumsIntent
import com.example.bebrascreens.presentation.albums.AlbumsUiState
import com.example.domain.repository.MediaStoreRepository
import com.example.widget.WidgetBridge
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val repository: MediaStoreRepository,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _state = MutableStateFlow(AlbumsUiState())
    val state = _state.asStateFlow()

    fun onIntent(intent: AlbumsIntent){
        when(intent){
            is AlbumsIntent.LoadAlbums -> loadAlbums()
            is AlbumsIntent.SelectedAlbum -> selectedAlbums(intent.albumId)
        }
    }
    private fun loadAlbums(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            repository.getAlbum()
                .onSuccess {album ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            albumUiModel = album.map { AlbumUiModel(it)},
                            isEmpty = album.isEmpty()
                        )
                    }
                }
                .onFailure {e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = e.message ?: "Ошибка загрузки"
                        )
                    }
                }
        }

    }

    private fun selectedAlbums(albumId: String){
        viewModelScope.launch {
            val album = _state.value.albumUiModel.find {it.album.id == albumId}
            _state.update { it.copy(
                albumSelected = album?.album,
                albumUiModel = it.albumUiModel.map {uiModel ->
                    if (uiModel.album.id == albumId) {
                        uiModel.copy(
                            isSelected = true,
                            backgroundColor = Color(0xFF3F6F7C)
                        )
                    } else {
                        uiModel.copy(
                            isSelected = false,
                            backgroundColor = Color(0xFF73B4C8)
                        )
                    }
                }
            )}
            WidgetBridge.setBucketId(context, album?.album?.id ?:"")
        }
    }
}