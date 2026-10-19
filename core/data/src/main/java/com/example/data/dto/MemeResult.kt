package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MemeResult(
    val id: Int,
    val url: String,
    val score: Float,
    val explanation: String? = null
)
