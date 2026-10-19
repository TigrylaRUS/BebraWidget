package com.example.domain.usecases

import com.example.domain.repository.MediaStoreRepository
import javax.inject.Inject

class GetRandomPhotoUseCase @Inject constructor(
    private val mediaStoreRepository: MediaStoreRepository
){
    suspend fun invoke(bucketId: String?): String?{
        val effectiveBucketId = when{
            bucketId.isNullOrBlank() -> null
            else -> bucketId
        }
        return mediaStoreRepository.getRandomPhotoForAlbum(effectiveBucketId)
    }
}