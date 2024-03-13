package com.bokorzslt.data.features.home.mapper

import com.bokorzslt.data.features.home.models.NetworkMovie
import com.bokorzslt.domain.features.home.models.Movie

fun NetworkMovie.asDomain(
    genreMapper: GenreMapper
): Movie =
    Movie(
        id = id,
        title = title,
        posterPath = buildImageUrl(posterPath, POSTER_SIZE),
        backdropPath = buildImageUrl(backdropPath, BACKDROP_SIZE),
        rating = voteAverage,
        genres = genreMapper.mapGenreIdsToStrings(genreIds)
    )

fun List<NetworkMovie>.asDomainList(genreMapper: GenreMapper): List<Movie> =
    map { it.asDomain(genreMapper) }

private fun buildImageUrl(path: String, size: String): String {
    if (path.isNotEmpty()) {
        return IMAGE_SERVICE_URL + size + path
    }
    return ""
}

private const val IMAGE_SERVICE_URL = "https://image.tmdb.org/t/p/"
private const val POSTER_SIZE = "w500"
private const val BACKDROP_SIZE = "w780"