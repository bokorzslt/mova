package com.bokorzslt.data.generic.extensions

fun String.buildPosterUrl() =
    buildImageUrl(this, POSTER_SIZE)

fun String.buildBackdropUrl() =
    buildImageUrl(this, BACKDROP_SIZE)

private fun buildImageUrl(path: String, size: String): String {
    if (path.isNotEmpty()) {
        return IMAGE_SERVICE_URL + size + path
    }
    return ""
}

private const val IMAGE_SERVICE_URL = "https://image.tmdb.org/t/p/"
private const val POSTER_SIZE = "w500"
private const val BACKDROP_SIZE = "w780"