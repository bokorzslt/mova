package com.bokorzslt.data.features.home.repository

import com.bokorzslt.data.features.home.datasource.remote.RemoteGenreDataSource
import com.bokorzslt.data.features.home.mapper.asDomainList
import com.bokorzslt.domain.features.home.models.Genre
import com.bokorzslt.domain.features.home.repository.GenreRepository

class GenreRepositoryImpl(
    private val remoteGenreDataSource: RemoteGenreDataSource
) : GenreRepository {

    private var cachedGenres: List<Genre> = emptyList()

    override suspend fun initialize() {
        cachedGenres = remoteGenreDataSource.getGenres().asDomainList()
    }

    override fun getGenres(): List<Genre> = cachedGenres
}