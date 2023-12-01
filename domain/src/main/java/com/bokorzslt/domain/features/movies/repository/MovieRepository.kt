package com.bokorzslt.domain.features.movies.repository

import com.bokorzslt.domain.features.movies.models.Movie

interface MovieRepository {

    suspend fun getNowPlayingMovies(): List<Movie>

}