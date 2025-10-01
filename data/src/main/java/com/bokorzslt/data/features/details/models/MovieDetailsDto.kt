package com.bokorzslt.data.features.details.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("genres")
    val genres: List<GenreDto>? = null,

    @SerialName("id")
    val id: Long,

    @SerialName("origin_country")
    val originCountries: List<String>? = null,

    @SerialName("overview")
    val overview: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double
)

