package com.bokorzslt.data.features.home.datasource.remote

import com.bokorzslt.data.features.home.models.NetworkGenre
import com.bokorzslt.data.generic.network.helpers.safeValidateResponse

class RemoteGenreDataSource(
    private val genreApiService: GenreApiService
) {
    suspend fun getGenres(): List<NetworkGenre> =
        genreApiService.getGenres().safeValidateResponse().genres
}