package com.bokorzslt.data.features.home.datasource.remote

import com.bokorzslt.data.features.home.models.NetworkMovie
import com.bokorzslt.data.generic.models.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/{category}")
    suspend fun getMoviesByCategory(@Path("category") category: String): Response<ResponseData<List<NetworkMovie>>>
}
