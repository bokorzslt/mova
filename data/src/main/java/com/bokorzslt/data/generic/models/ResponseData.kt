package com.bokorzslt.data.generic.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseData<T>(

    @SerialName("id")
    val id: Int? = null,

    @SerialName("page")
    val page: Int? = null,

    @SerialName("results")
    val results: T
)
