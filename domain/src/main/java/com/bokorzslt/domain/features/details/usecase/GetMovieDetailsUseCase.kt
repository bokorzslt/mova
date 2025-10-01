package com.bokorzslt.domain.features.details.usecase

import com.bokorzslt.domain.features.details.models.MovieDetails
import com.bokorzslt.domain.features.home.repository.MovieRepository

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Long): MovieDetails =
        movieRepository.getMovieDetails(movieId)
}