package com.example.widget

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.Image
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.ImageProvider
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.layout.size

class BebraWidget: GlanceAppWidget(){
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val bucketId = WidgetBridge.getBucketId(context)
        val photoUri = WidgetDataStore(context).getPhotoUri()
        val memeUrl = WidgetDataStore(context).getMemeUrl()
        provideContent {
            WidgetContent(photoUri, memeUrl)
        }
    }
}

@Composable
fun WidgetContent(photoUri: String?, memeUrl: String?) {
    Column{
        if (photoUri != null) {
            Image(
                provider = ImageProvider(Uri.parse(photoUri)),
                contentDescription = null,
                modifier = androidx.glance.GlanceModifier.size(150.dp)
            )
        }
        if (memeUrl != null) {
            Image(
                provider = ImageProvider(Uri.parse(memeUrl)),
                contentDescription = null,
                modifier = androidx.glance.GlanceModifier.size(150.dp)
            )
        }
    }
}