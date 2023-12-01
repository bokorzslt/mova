package com.bokorzslt.data.generic.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(QUERY_PARAM_API_KEY, API_KEY)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val API_KEY = "16bb437c93b00c97bb690de90cbff3d1"
    }
}