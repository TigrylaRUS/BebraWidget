package com.example.data.repository

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.example.data.dto.AlbumDto
import com.example.data.mapper.AlbumMapper
import com.example.domain.model.Album
import com.example.domain.repository.MediaStoreRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaStoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): MediaStoreRepository {
    override suspend fun getAlbum(): Result<List<Album>> = withContext(Dispatchers.IO) {
        runCatching {
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME
            )

            val cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                "${MediaStore.Images.Media.DATE_ADDED} DESC"
            )

            val album = mutableMapOf<Long, AlbumDto>()

            cursor?.use { c ->
                val idCol = c.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val bucketIdCol = c.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID)
                val bucketNameCol = c.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
                while (c.moveToNext()) {
                    val id = c.getLong(idCol)
                    val bucketId = c.getLong(bucketIdCol)
                    val bucketName = c.getString(bucketNameCol) ?: "Unknown"
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    val existing = album[bucketId]
                    if (existing == null) {
                        album[bucketId] = AlbumDto(bucketId, bucketName, uri, 1)
                    } else {
                        album[bucketId] = existing.copy(photoCount = existing.photoCount + 1)
                    }
                }
            }
            AlbumMapper.toDomain(album.values.toList())
        }
    }

    override suspend fun getRandomPhotoForAlbum(bucketId: String?): String? {
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = if(bucketId == null) null
        else "${MediaStore.Images.Media.BUCKET_ID} = ?"
        val selectionArgs = if (bucketId == null) null else arrayOf(bucketId)
        context.contentResolver.query(
            uri,
            projection,
            selection,
            selectionArgs,
            "RANDOM()"
        )?.use { cursor ->
            if(cursor.moveToNext()){
                val id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                )
                return ContentUris.withAppendedId(uri, id).toString()
            }
        }
        return null
    }
}