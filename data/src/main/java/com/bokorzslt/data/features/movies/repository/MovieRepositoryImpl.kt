package com.bokorzslt.data.features.movies.repository

import com.bokorzslt.data.features.movies.datasource.remote.RemoteMovieDataSource
import com.bokorzslt.data.features.movies.mapper.asDomainList
import com.bokorzslt.domain.features.movies.models.Movie
import com.bokorzslt.domain.features.movies.repository.MovieRepository


class MovieRepositoryImpl(
    private val remoteMovieDataSource: RemoteMovieDataSource
) : MovieRepository {

    override suspend fun getNowPlayingMovies(): List<Movie> =
        remoteMovieDataSource.getNowPlayingMovies().asDomainList()
}