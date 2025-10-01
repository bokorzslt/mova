package com.bokorzslt.domain.features.details.usecase

import com.bokorzslt.domain.features.home.models.Movie
import com.bokorzslt.domain.features.home.repository.MovieRepository

class GetSimilarMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: Long): List<Movie> =
        movieRepository.getSimilarMovies(movieId)
}