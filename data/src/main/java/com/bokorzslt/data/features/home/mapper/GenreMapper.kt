package com.bokorzslt.data.features.home.mapper

import com.bokorzslt.data.features.home.models.NetworkGenre
import com.bokorzslt.domain.features.home.models.Genre
import com.bokorzslt.domain.features.home.repository.GenreRepository

fun NetworkGenre.asDomain(): Genre =
    Genre(
        id = id,
        name = name
    )

fun List<NetworkGenre>.asDomainList(): List<Genre> =
    map {
        it.asDomain()
    }

class GenreMapper(
    private val genreRepository: GenreRepository
) {
    private var genreMap: Map<Int, String> = emptyMap()

    fun mapGenreIdsToStrings(genreIds: List<Int>): List<String> {
        if (genreMap.isEmpty()) {
            genreMap = genreRepository.getGenres().associateBy(Genre::id, Genre::name)
        }
        return genreIds.map { genreMap[it].toString() }
    }
}