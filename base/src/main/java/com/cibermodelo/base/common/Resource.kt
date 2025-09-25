package com.cibermodelo.base.common

sealed class Resource<T>(val data: T? = null, val code: Int? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(code: Int, message: String, data: T? = null) : Resource<T>(data, code, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}