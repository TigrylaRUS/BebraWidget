package com.example.data.retrofit

import com.example.data.dto.MemeSearchRequest
import com.example.data.dto.MemeSearhResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface clientAPI {
    @POST("v1/images/reverse-search")
    suspend fun getMem(
        @Body request: MemeSearchRequest
    ): Response<MemeSearhResponse>
}
