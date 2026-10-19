package com.example.domain.repository

import com.example.domain.model.Album

interface MediaStoreRepository {
    suspend fun getAlbum(): Result<List<Album>>
    suspend fun getRandomPhotoForAlbum(bucketId: String?): String?
}