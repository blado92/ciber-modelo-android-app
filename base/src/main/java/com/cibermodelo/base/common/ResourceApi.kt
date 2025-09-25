package com.cibermodelo.base.common

sealed class ResourceApi<T>(
    val data: T? = null,
    val errorCode: Int? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : ResourceApi<T>(data)
    class Error<T>(errorCode: Int, errorMessage: String) :
        ResourceApi<T>(errorCode = errorCode, errorMessage = errorMessage)
}