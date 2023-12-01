package com.bokorzslt.data.generic.network.helpers

import android.content.Context
import com.bokorzslt.data.generic.network.interceptor.AuthInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import java.io.File

object OkHttpCreator {

    private const val CACHE_SIZE_MEGA_BYTES = 250 * 1024 * 1024L
    private const val CACHE_FOLDER = "okhttp"

    fun createOkHttpClient(
        context: Context
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, CACHE_FOLDER), CACHE_SIZE_MEGA_BYTES))
            .addInterceptor(AuthInterceptor())

        if (BuildConfig.DEBUG) {
            // Enable HTTP logging on debug builds
            builder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
        return builder.build()
    }
}