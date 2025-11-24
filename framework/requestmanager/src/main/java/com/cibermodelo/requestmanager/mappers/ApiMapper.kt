package com.cibermodelo.requestmanager.mappers

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.AccessLevel
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.QueryTypes
import com.cibermodelo.base.model.Role
import com.cibermodelo.base.model.User
import com.cibermodelo.requestmanager.model.AccessLevelResponse
import com.cibermodelo.requestmanager.model.DeceasedResponse
import com.cibermodelo.requestmanager.model.QueryTypesResponse
import com.cibermodelo.requestmanager.model.RoleResponse
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

fun RoleResponse.toRole() = Role(
    id = id,
    name = name,
    description = description
)

fun AccessLevelResponse.toAccessLevel() = AccessLevel(
    id = id,
    name = name
)

fun QueryTypesResponse.toQueryTypes() = QueryTypes(
    id = id,
    name = name,
    role = role.toRole(),
    accessLevel = accessLevel.toAccessLevel()
)

fun List<QueryTypesResponse>.toQueryTypesList() = map(QueryTypesResponse::toQueryTypes)