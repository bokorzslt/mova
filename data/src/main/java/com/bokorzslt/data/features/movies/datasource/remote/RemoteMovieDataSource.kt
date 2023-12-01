package com.bokorzslt.data.features.movies.datasource.remote

import com.bokorzslt.data.features.movies.models.NetworkMovie
import com.bokorzslt.data.generic.network.helpers.safeValidateResponse

class RemoteMovieDataSource(
    private val movieApiService: MovieApiService
) {
    suspend fun getNowPlayingMovies(): List<NetworkMovie> =
        movieApiService.getNowPlayingMovies().safeValidateResponse().results
}
