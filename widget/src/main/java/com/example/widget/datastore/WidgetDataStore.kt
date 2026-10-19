package com.example.widget

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.widget.datastore.WidgetKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

val Context.widgetDataStore by preferencesDataStore("widget_prefs")

class WidgetDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getBucketId(): String? {
        return context.widgetDataStore.data.first()[WidgetKeys.BUCKET_ID]
    }

    suspend fun getPhotoUri(): String? {
        return context.widgetDataStore.data.first()[WidgetKeys.PHOTO_URI]
    }

    suspend fun getMemeUrl(): String? {
        return context.widgetDataStore.data.first()[WidgetKeys.MEME_URL]
    }

    suspend fun saveBucketId(bucketId: String) {
        context.widgetDataStore.edit { prefs ->
            prefs[WidgetKeys.BUCKET_ID] = bucketId
        }
    }

    suspend fun savePhotoAndMeme(photoUri: String, memeUrl: String) {
        context.widgetDataStore.edit { prefs ->
            prefs[WidgetKeys.PHOTO_URI] = photoUri
            prefs[WidgetKeys.MEME_URL] = memeUrl
        }
    }

    suspend fun clear() {
        context.widgetDataStore.edit { it.clear() }
    }

}