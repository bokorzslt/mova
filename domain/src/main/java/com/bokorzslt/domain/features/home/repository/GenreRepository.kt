package com.bokorzslt.domain.features.home.repository

import com.bokorzslt.domain.features.home.models.Genre

interface GenreRepository {

    suspend fun initialize()

    fun getGenres(): List<Genre>
}