package com.bokorzslt.data.features.home.datasource.remote

import com.bokorzslt.data.features.home.models.GenresResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenreApiService {
    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresResponse>
}