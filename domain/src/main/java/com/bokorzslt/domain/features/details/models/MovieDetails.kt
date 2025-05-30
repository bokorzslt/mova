package com.bokorzslt.domain.features.details.models

data class MovieDetails(
    val id: Long,
    val title: String,
    val backdropUrl: String,
    val rating: Double,
    val releaseYear: Int,
    val originCountry: String,
    val description: String,
    val genres: List<String>,
    val castList: List<Cast>
)