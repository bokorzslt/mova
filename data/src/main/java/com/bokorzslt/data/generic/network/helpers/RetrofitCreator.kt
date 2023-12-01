package com.bokorzslt.data.generic.network.helpers

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitCreator {

    private const val SERIALISATION_MEDIA_TYPE = "application/json"
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = SERIALISATION_MEDIA_TYPE.toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            coerceInputValues = true
        }

        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }
}