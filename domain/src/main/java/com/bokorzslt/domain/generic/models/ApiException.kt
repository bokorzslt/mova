package com.bokorzslt.domain.generic.models

class ApiException(
    errorCode: Int,
    errorMessage: String
) : Exception("$errorCode $errorMessage")