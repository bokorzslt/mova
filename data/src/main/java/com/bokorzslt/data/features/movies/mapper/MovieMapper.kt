package com.bokorzslt.data.features.movies.mapper

import com.bokorzslt.data.features.movies.models.NetworkMovie
import com.bokorzslt.domain.features.movies.models.Movie

fun NetworkMovie.asDomain(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        backdropPath = backdropPath,
        posterPath = posterPath
    )

fun List<NetworkMovie>.asDomainList(): List<Movie> =
    map { it.asDomain() }