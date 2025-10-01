package com.bokorzslt.data.features.details.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrewDto(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String? = null,

    @SerialName("profile_path")
    val profilePath: String? = null,

    @SerialName("job")
    val job: String? = null
)
