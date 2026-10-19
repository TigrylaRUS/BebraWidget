package com.example.data.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MemeClient {
    @Provides
    @Singleton
    fun MemeClientBuilder(): clientAPI{
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY  // ← логирует тело запроса и ответа
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.memestack.ai/")
            .client(client)  // ← ДОБАВЬ клиент с логированием
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(clientAPI::class.java)
    }
}
