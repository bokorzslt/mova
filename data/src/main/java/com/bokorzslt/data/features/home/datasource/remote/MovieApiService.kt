package com.bokorzslt.data.features.home.datasource.remote

import com.bokorzslt.data.features.details.models.CreditsDto
import com.bokorzslt.data.features.details.models.MovieDetailsDto
import com.bokorzslt.data.features.details.models.TrailerDto
import com.bokorzslt.data.features.home.models.MovieDto
import com.bokorzslt.data.generic.models.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/{category}")
    suspend fun getMoviesByCategory(@Path("category") category: String): Response<ResponseData<List<MovieDto>>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Long): Response<MovieDetailsDto>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Long): Response<CreditsDto>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(@Path("movie_id") movieId: Long): Response<ResponseData<List<TrailerDto>>>
}
