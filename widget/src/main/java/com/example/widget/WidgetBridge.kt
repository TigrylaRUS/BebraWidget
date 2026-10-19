package com.example.widget

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.updateAll
object WidgetBridge {
    suspend fun setBucketId(context: Context, bucketId: String) {
        Log.d("WidgetBridge", "setBucketId called: $bucketId")
        try {
            WidgetDataStore(context).saveBucketId(bucketId)
            Log.d("WidgetBridge", "bucketId saved to DataStore")

            BebraWidget().updateAll(context)
            Log.d("WidgetBridge", "widget updateAll called")
        } catch (e: Exception) {
            Log.e("WidgetBridge", "error", e)
        }
    }

    suspend fun getBucketId(context: Context): String? {
        val bucketId = WidgetDataStore(context).getBucketId()
        Log.d("WidgetBridge", "getBucketId: $bucketId")
        return bucketId
    }
}