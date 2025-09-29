package com.bokorzslt.domain.features.details.models

data class Trailer(
    val id: String,
    val name: String,
    val imageUrl: String,
    val provider: TrailerProvider
)
