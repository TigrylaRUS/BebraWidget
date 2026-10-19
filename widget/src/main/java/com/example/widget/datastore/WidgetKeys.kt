package com.example.widget.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object WidgetKeys {
    val BUCKET_ID = stringPreferencesKey("widget_bucket_id")
    val PHOTO_URI = stringPreferencesKey("widget_photo_uri")
    val MEME_URL = stringPreferencesKey("widget_meme_url")
}