package com.bokorzslt.domain.features.home.repository

import com.bokorzslt.domain.features.details.models.MovieDetails
import com.bokorzslt.domain.features.details.models.Trailer
import com.bokorzslt.domain.features.home.models.Movie

interface MovieRepository {
    suspend fun getMoviesByCategory(category: String): List<Movie>

    suspend fun getMovieDetails(movieId: Long): MovieDetails

    suspend fun getMovieTrailers(movieId: Long): List<Trailer>
}