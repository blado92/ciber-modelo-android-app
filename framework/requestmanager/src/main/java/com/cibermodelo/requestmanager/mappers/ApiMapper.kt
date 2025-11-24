package com.cibermodelo.requestmanager.mappers

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.User
import com.cibermodelo.requestmanager.model.DeceasedResponse
import com.cibermodelo.requestmanager.model.UserResponse
import retrofit2.Response
import java.util.Date
import kotlin.Int

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

fun DeceasedResponse.toDeceased() = Deceased(
    id,
    name,
    lastName,
    email,
    address,
    eps,
    birthday,
    deceasedDate
)

fun List<DeceasedResponse>.toDeceasedList() = map(DeceasedResponse::toDeceased)