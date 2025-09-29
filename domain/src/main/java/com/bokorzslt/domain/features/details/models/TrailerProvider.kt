package com.bokorzslt.domain.features.details.models

enum class TrailerProvider {
    YOUTUBE,
    UNKNOWN;

    companion object {
        fun from(providerName: String) =
            entries.firstOrNull { it.name.equals(providerName, ignoreCase = true) } ?: UNKNOWN
    }
}