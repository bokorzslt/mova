package com.bokorzslt.data.features.details.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerDto(
    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("key")
    val key: String? = null,

    @SerialName("site")
    val site: String? = null
)
