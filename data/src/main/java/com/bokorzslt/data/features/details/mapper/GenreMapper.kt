package com.bokorzslt.data.features.details.mapper

import com.bokorzslt.data.features.details.models.GenreDto

fun List<GenreDto>.toGenreList(): List<String> =
    map { it.name }