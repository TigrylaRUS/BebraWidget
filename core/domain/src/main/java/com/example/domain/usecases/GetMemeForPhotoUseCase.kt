package com.example.domain.usecases

import com.example.domain.model.MemeData
import com.example.domain.repository.MediaStoreRepository
import com.example.domain.repository.MemeStoreRepository
import javax.inject.Inject

class GetMemeForPhotoUseCase @Inject constructor(
    private val mediaStoreRepository: MediaStoreRepository,
    private val memeStoreRepository: MemeStoreRepository
) {
    suspend operator fun invoke(bucketId: String?): MemeData? {
        // 1. Получить фото
        val photoUri = mediaStoreRepository.getRandomPhotoForAlbum(bucketId) ?: return null

        // 2. Отправить фото на сервер, получить URL мема
        val memeUrl = memeStoreRepository.getMemeForAlbum(photoUri)
            ?: return null

        // 3. Собрать MemeData
        return MemeData(photoUri = photoUri, memeUrl = memeUrl)
    }
}