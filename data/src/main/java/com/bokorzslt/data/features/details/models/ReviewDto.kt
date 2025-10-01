package com.bokorzslt.data.features.details.models

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class ReviewDto(
    @SerialName("id")
    val id: String? = null,

    @SerialName("author_details")
    val authorDetails: AuthorDetails? = null,

    @SerialName("content")
    val content: String? = null,

    @SerialName("created_at")
    val createdAt: String? = null
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
class AuthorDetails(
    @SerialName("name")
    val name: String? = null,

    @SerialName("username")
    val username: String? = null,

    @SerialName("avatar_path")
    val avatarPath: String? = null
)