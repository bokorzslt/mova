package com.bokorzslt.domain.features.movies.models

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val posterPath: String
)