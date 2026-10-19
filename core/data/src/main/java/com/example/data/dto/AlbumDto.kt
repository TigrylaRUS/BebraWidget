package com.example.data.dto

import android.net.Uri

data class AlbumDto(
    val bucketId: Long,
    val bucketName: String,
    val coverUri: Uri,
    val photoCount: Int
)
