package com.cibermodelo.requestmanager.mappers

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.User
import com.cibermodelo.requestmanager.model.UserResponse
import retrofit2.Response

fun <T> Response<T>.toResource(): ResourceApi<T> {
    return if (this.isSuccessful) {
        val body = this.body()
        if (body != null) {
            ResourceApi.Success(body)
        } else {
            ResourceApi.Error(errorCode = 1, errorMessage = "Empty body")
        }
    } else {
        ResourceApi.Error(errorCode = this.code(), this.message())
    }
}

fun UserResponse.toUser() = User(
    id,
    name,
    lastName,
    email
)