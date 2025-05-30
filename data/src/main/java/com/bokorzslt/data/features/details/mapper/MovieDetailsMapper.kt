package com.bokorzslt.data.features.details.mapper

import com.bokorzslt.data.features.details.models.MovieDetailsDto
import com.bokorzslt.data.generic.extensions.buildBackdropUrl
import com.bokorzslt.domain.features.details.models.Cast
import com.bokorzslt.domain.features.details.models.MovieDetails

fun MovieDetailsDto.toMovieDetails(cast: List<Cast>): MovieDetails =
    MovieDetails(
        id = id,
        title = title.orEmpty(),
        backdropUrl = backdropPath?.buildBackdropUrl().orEmpty(),
        rating = voteAverage,
        releaseYear = releaseDate?.toReleaseYear() ?: 0,
        originCountry = originCountries?.firstOrNull().orEmpty(),
        description = overview.orEmpty(),
        genres = genres?.toGenreList() ?: emptyList(),
        castList = cast
    )

private fun String.toReleaseYear(): Int =
    takeIf { it.length >= 4 }
        ?.substring(0, 4)
        ?.toIntOrNull() ?: 0