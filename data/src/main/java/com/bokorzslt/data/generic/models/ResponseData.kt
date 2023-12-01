package com.bokorzslt.data.generic.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseData<T>(

    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val results: T
)
