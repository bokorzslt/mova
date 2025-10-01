package com.bokorzslt.domain.features.details.usecase

import com.bokorzslt.domain.features.details.models.Trailer
import com.bokorzslt.domain.features.home.repository.MovieRepository

class GetMovieTrailersUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Long): List<Trailer> =
        movieRepository.getMovieTrailers(movieId)
}