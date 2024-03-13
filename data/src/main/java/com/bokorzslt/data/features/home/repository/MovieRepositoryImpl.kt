package com.bokorzslt.data.features.home.repository

import com.bokorzslt.data.features.home.datasource.remote.RemoteMovieDataSource
import com.bokorzslt.data.features.home.mapper.GenreMapper
import com.bokorzslt.data.features.home.mapper.asDomainList
import com.bokorzslt.domain.features.home.models.Movie
import com.bokorzslt.domain.features.home.repository.MovieRepository


class MovieRepositoryImpl(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val genreMapper: GenreMapper
) : MovieRepository {

    override suspend fun getMoviesByCategory(category: String): List<Movie> =
        remoteMovieDataSource.getMoviesByCategory(category).asDomainList(genreMapper)
}