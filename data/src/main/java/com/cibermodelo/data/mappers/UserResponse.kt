package com.cibermodelo.data.mappers

import com.cibermodelo.base.model.User

fun User.toUser() = User(
    id,
    name,
    lastName,
    email
)