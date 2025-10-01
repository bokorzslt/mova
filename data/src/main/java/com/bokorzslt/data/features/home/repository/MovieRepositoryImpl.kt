package com.bokorzslt.data.features.home.repository

import com.bokorzslt.data.features.details.mapper.mapCastToCastList
import com.bokorzslt.data.features.details.mapper.mapCrewToCastList
import com.bokorzslt.data.features.details.mapper.toMovieDetails
import com.bokorzslt.data.features.details.mapper.toReviewList
import com.bokorzslt.data.features.details.mapper.toTrailersList
import com.bokorzslt.data.features.home.datasource.remote.RemoteMovieDataSource
import com.bokorzslt.data.features.home.mapper.toMovieList
import com.bokorzslt.domain.features.details.models.MovieDetails
import com.bokorzslt.domain.features.details.models.Review
import com.bokorzslt.domain.features.details.models.Trailer
import com.bokorzslt.domain.features.home.models.Movie
import com.bokorzslt.domain.features.home.repository.MovieRepository


class MovieRepositoryImpl(
    private val remoteMovieDataSource: RemoteMovieDataSource
) : MovieRepository {

    override suspend fun getMoviesByCategory(category: String): List<Movie> =
        remoteMovieDataSource.getMoviesByCategory(category).toMovieList()

    override suspend fun getMovieDetails(movieId: Long): MovieDetails {
        val details = remoteMovieDataSource.getMovieDetails(movieId)
        val credits = remoteMovieDataSource.getMovieCredits(movieId)

        val castList = buildList {
            credits.cast?.mapCastToCastList()?.let(::addAll)
            credits.crew?.mapCrewToCastList()?.let(::addAll)
        }
        return details.toMovieDetails(castList)
    }

    override suspend fun getMovieTrailers(movieId: Long): List<Trailer> =
        remoteMovieDataSource.getMovieTrailers(movieId).toTrailersList()

    override suspend fun getSimilarMovies(movieId: Long): List<Movie> =
        remoteMovieDataSource.getSimilarMovies(movieId).toMovieList()

    override suspend fun getMovieReviews(movieId: Long): List<Review> =
        remoteMovieDataSource.getMovieReviews(movieId).toReviewList()
}