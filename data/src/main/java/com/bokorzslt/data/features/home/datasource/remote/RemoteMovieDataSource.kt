package com.bokorzslt.data.features.home.datasource.remote

import com.bokorzslt.data.features.details.models.CreditsDto
import com.bokorzslt.data.features.details.models.MovieDetailsDto
import com.bokorzslt.data.features.details.models.TrailerDto
import com.bokorzslt.data.features.home.models.MovieDto
import com.bokorzslt.data.generic.network.helpers.safeValidateResponse

class RemoteMovieDataSource(
    private val movieApiService: MovieApiService
) {
    suspend fun getMoviesByCategory(category: String): List<MovieDto> =
        movieApiService.getMoviesByCategory(category).safeValidateResponse().results

    suspend fun getMovieDetails(movieId: Long): MovieDetailsDto =
        movieApiService.getMovieDetails(movieId).safeValidateResponse()

    suspend fun getMovieCredits(movieId: Long): CreditsDto =
        movieApiService.getMovieCredits(movieId).safeValidateResponse()

    suspend fun getMovieTrailers(movieId: Long): List<TrailerDto> =
        movieApiService.getMovieTrailers(movieId).safeValidateResponse().results
}
