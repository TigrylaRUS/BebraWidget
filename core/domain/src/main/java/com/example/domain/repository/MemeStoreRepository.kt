package com.example.domain.repository

interface MemeStoreRepository {
    suspend fun getMemeForAlbum(bucketId: String): String?
}