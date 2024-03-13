package com.bokorzslt.domain.features.home.models


data class Movie(
    val id: Long,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val rating: Double,
    val genres: List<String>
)