package com.bokorzslt.data.features.details.mapper

import com.bokorzslt.data.features.details.models.CastDto
import com.bokorzslt.data.features.details.models.CrewDto
import com.bokorzslt.data.generic.extensions.buildPosterUrl
import com.bokorzslt.domain.features.details.models.Cast

fun CastDto.toCast(): Cast =
    Cast(
        name = name.orEmpty(),
        profileUrl = profilePath?.buildPosterUrl().orEmpty(),
        job = knownForDepartment.orEmpty()
    )

fun CrewDto.toCast(): Cast =
    Cast(
        name = name.orEmpty(),
        profileUrl = profilePath?.buildPosterUrl().orEmpty(),
        job = job.orEmpty()
    )

fun List<CastDto>.mapCastToCastList(): List<Cast> =
    map { it.toCast() }

fun List<CrewDto>.mapCrewToCastList(): List<Cast> =
    map { it.toCast() }
