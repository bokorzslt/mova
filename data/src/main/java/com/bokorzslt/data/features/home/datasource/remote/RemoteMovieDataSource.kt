package com.bokorzslt.data.features.home.datasource.remote

import com.bokorzslt.data.features.home.models.NetworkMovie
import com.bokorzslt.data.generic.network.helpers.safeValidateResponse

class RemoteMovieDataSource(
    private val movieApiService: MovieApiService
) {
    suspend fun getMoviesByCategory(category: String): List<NetworkMovie> =
        movieApiService.getMoviesByCategory(category).safeValidateResponse().results
}
