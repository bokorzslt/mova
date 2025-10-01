package com.bokorzslt.data.features.details.mapper

import com.bokorzslt.data.features.details.models.ReviewDto
import com.bokorzslt.data.generic.extensions.buildAvatarUrl
import com.bokorzslt.domain.features.details.models.Review

fun ReviewDto.toReview(): Review =
    Review(
        id = id.orEmpty(),
        authorName = if (!authorDetails?.name.isNullOrEmpty()) {
            authorDetails?.name.orEmpty()
        } else {
            authorDetails?.username.orEmpty()
        },
        avatarUrl = authorDetails?.avatarPath?.buildAvatarUrl().orEmpty(),
        content = content.orEmpty(),
        createdAt = createdAt?.substring(0, 10).orEmpty()
    )

fun List<ReviewDto>.toReviewList(): List<Review> =
    map { it.toReview() }

