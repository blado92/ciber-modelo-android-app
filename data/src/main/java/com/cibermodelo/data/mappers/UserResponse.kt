package com.cibermodelo.data.mappers

import com.cibermodelo.domain.model.User
import com.cibermodelo.requestmanager.model.UserResponse

fun UserResponse.toUser() = User(
    id,
    name,
    lastName,
    email
)