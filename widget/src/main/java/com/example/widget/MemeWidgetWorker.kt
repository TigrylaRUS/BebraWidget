package com.example.widget

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.updateAll
import com.example.domain.usecases.GetMemeForPhotoUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

@HiltWorker
class MemeWidgetWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val getMemeForAlbum: GetMemeForPhotoUseCase,
    private val widgetDataStore: WidgetDataStore
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            val bucketId = widgetDataStore.getBucketId()

            val data = getMemeForAlbum(bucketId)

            if (data == null) {
                return Result.retry()
            }

            widgetDataStore.savePhotoAndMeme(data.photoUri, data.memeUrl)

            BebraWidget().updateAll(applicationContext)

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}