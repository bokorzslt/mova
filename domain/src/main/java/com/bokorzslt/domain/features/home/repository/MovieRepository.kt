package com.bokorzslt.domain.features.home.repository

import com.bokorzslt.domain.features.home.models.Movie

interface MovieRepository {
    suspend fun getMoviesByCategory(category: String): List<Movie>
}