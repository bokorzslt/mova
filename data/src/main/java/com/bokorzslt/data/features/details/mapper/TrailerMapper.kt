package com.bokorzslt.data.features.details.mapper

import android.util.Log
import com.bokorzslt.data.features.details.models.TrailerDto
import com.bokorzslt.domain.features.details.models.Trailer
import com.bokorzslt.domain.features.details.models.TrailerProvider

fun TrailerDto.toTrailer(): Trailer {
    val provider = TrailerProvider.from(site.orEmpty())
    return Trailer(
        id = id.orEmpty(),
        name = name.orEmpty(),
        imageUrl = buildTrailerThumbUrl(provider, key.orEmpty()),
        provider = provider
    )
}

fun List<TrailerDto>.toTrailersList(): List<Trailer> =
    map { it.toTrailer() }

private fun buildTrailerThumbUrl(provider: TrailerProvider, key: String) =
    when (provider) {
        TrailerProvider.YOUTUBE -> YOUTUBE_IMAGE_SERVICE_URL.replace(YOUTUBE_KEY_PLACEHOLDER, key)
        else -> ""
    }


private const val YOUTUBE_KEY_PLACEHOLDER = "{key}"
private const val YOUTUBE_IMAGE_SERVICE_URL = "https://img.youtube.com/vi/{key}/hqdefault.jpg"