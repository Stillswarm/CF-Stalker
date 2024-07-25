package com.example.demorequest.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://codeforces.com/api/"

val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface CfApiService {
    @GET("user.info")
    suspend fun getInfo(@Query("handles") handles: String): InfoList
}

object CfApi {
    val retrofitService: CfApiService by lazy {
        retrofit.create(CfApiService::class.java)
    }
}