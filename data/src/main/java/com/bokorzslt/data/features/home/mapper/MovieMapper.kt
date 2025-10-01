package com.bokorzslt.data.features.home.mapper


import com.bokorzslt.data.features.home.models.MovieDto
import com.bokorzslt.data.generic.extensions.buildPosterUrl
import com.bokorzslt.domain.features.home.models.Movie

fun MovieDto.toMovie(): Movie =
    Movie(
        id = id,
        posterUrl = posterPath?.buildPosterUrl().orEmpty(),
        rating = voteAverage,
    )

fun List<MovieDto>.toMovieList(): List<Movie> =
    map { it.toMovie() }

