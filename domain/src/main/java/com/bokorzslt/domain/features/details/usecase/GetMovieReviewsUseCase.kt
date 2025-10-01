package com.bokorzslt.domain.features.details.usecase

import com.bokorzslt.domain.features.home.repository.MovieRepository

class GetMovieReviewsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(movieId: Long) =
        movieRepository.getMovieReviews(movieId)
}