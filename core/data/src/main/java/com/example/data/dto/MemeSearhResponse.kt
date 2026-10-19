package com.example.data.dto


data class MemeSearhResponse(
    val similar: List<MemeMatch>?,   // ← similar, не results
    val input_meta: InputMeta? = null
)
