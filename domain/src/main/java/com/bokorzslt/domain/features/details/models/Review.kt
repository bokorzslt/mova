package com.bokorzslt.domain.features.details.models

data class Review(
    val id: String,
    val authorName: String,
    val avatarUrl: String,
    val content: String,
    val createdAt: String
)