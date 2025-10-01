package com.bokorzslt.data.features.details.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String
)