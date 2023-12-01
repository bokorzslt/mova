package com.bokorzslt.data.features.movies.datasource.remote

import com.bokorzslt.data.features.movies.models.NetworkMovie
import com.bokorzslt.data.generic.models.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<ResponseData<List<NetworkMovie>>>
}
