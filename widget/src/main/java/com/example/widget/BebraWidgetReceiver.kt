package com.example.widget

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class BebraWidgetReceiver: GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = BebraWidget()
    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        Log.d("BebraWidgetReceiver", "onEnabled called")  // ← ДОБАВЬ

        // Запусти Worker для теста
        val request = OneTimeWorkRequestBuilder<MemeWidgetWorker>().build()
        WorkManager.getInstance(context).enqueue(request)
        Log.d("BebraWidgetReceiver", "worker enqueued")  // ← ДОБАВЬ
    }
}