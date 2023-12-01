package com.bokorzslt.data.generic.network.helpers

import com.bokorzslt.domain.generic.models.ApiException
import retrofit2.Response

fun <T> Response<T>.safeValidateResponse(): T {
    if (!isSuccessful) {
        throw ApiException(code(), errorBody()?.string() ?: "Something went wrong.")
    }

    return body() ?: throw ApiException(code(), "Response body is null.")
}