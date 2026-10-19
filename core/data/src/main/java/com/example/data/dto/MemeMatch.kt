package com.example.data.dto

data class MemeMatch(
    val id: String,              // UUID изображения
    val url: String,             // ← ЭТО нужно тебе! Прямая ссылка на картинку
    val caption: String? = null, // AI-описание
    val alt_text: String? = null,// текст для accessibility
    val tags: List<String>? = null, // теги
    val zap_total_sats: Int? = null, // рейтинг (zaps)
    val text_in_image: String? = null, // OCR-текст
    val controversial: Boolean? = null, // флаг контента
    val width: Int? = null,
    val height: Int? = null
)