package com.example.data.dto

data class MemeSearchRequest(
    val url: String,
    val limit: Int = 10,     // ← добавь
    val content: String = "all"
)
