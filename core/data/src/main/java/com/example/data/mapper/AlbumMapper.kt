package com.example.data.mapper

import com.example.data.dto.AlbumDto
import com.example.domain.model.Album

object AlbumMapper{
    fun toDomain(dto: List<AlbumDto>): List<Album>{
        val domain = mutableListOf<Album>()
        dto.forEach {it ->
            domain.add(Album(it.bucketId.toString(), it.bucketName, it.coverUri.toString(), it.photoCount))
        }
        return domain
    }
}
