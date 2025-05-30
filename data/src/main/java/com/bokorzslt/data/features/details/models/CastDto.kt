package com.bokorzslt.data.features.details.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    @SerialName("id")
    val id: Int,

    @SerialName("known_for_department")
    val knownForDepartment: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("profile_path")
    val profilePath: String? = null
)
