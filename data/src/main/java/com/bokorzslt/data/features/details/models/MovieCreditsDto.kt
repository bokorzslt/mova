package com.bokorzslt.data.features.details.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsDto(
    @SerialName("id")
    val id: Int,

    @SerialName("cast")
    val cast: List<CastDto>? = null,

    @SerialName("crew")
    val crew: List<CrewDto>? = null
)