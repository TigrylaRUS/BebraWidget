package com.example.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import com.example.data.dto.MemeSearchRequest
import com.example.data.retrofit.clientAPI
import com.example.domain.repository.MemeStoreRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class MemeStoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: clientAPI
) : MemeStoreRepository {

    override suspend fun getMemeForAlbum(photoUri: String): String? {
        Log.d("MemeRepo", "getMemeForAlbum: $photoUri")

        val inputStream = context.contentResolver.openInputStream(Uri.parse(photoUri))
        if (inputStream == null) {
            Log.d("MemeRepo", "inputStream is null")
            return null
        }

        val bitmap = BitmapFactory.decodeStream(inputStream)
        if (bitmap == null) {
            Log.d("MemeRepo", "bitmap is null")
            return null
        }
        Log.d("MemeRepo", "bitmap size: ${bitmap.width}x${bitmap.height}")

        // Уменьшаем до 1024px
        val maxSize = 1024
        val scale = minOf(maxSize.toFloat() / bitmap.width, maxSize.toFloat() / bitmap.height)
        val scaledBitmap = if (scale < 1f) {
            Bitmap.createScaledBitmap(
                bitmap,
                (bitmap.width * scale).toInt(),
                (bitmap.height * scale).toInt(),
                true
            )
        } else bitmap

        Log.d("MemeRepo", "scaled size: ${scaledBitmap.width}x${scaledBitmap.height}")

        val outputStream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        val base64 = Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP)
        Log.d("MemeRepo", "base64 length: ${base64.length}")

        val dataUrl = "data:image/jpeg;base64,$base64"

        val response = api.getMem(MemeSearchRequest(url = dataUrl))
        Log.d("MemeRepo", "response code: ${response.code()}")
        Log.d("MemeRepo", "results: ${response.body()?.similar}")
        Log.d("MemeRepo", "count: ${response.body()?.similar?.size}")

        return response.body()?.similar?.firstOrNull()?.url
    }
}