package com.cibermodelo.databasemanager.mappers

import com.cibermodelo.base.model.User
import com.cibermodelo.databasemanager.entities.UserEntity

fun UserEntity.toUser() = User(
    id = this.id,
    name = this.name,
    lastName = this.lastName,
    email = this.email
)

fun User.toUserEntity() = UserEntity(
    id = this.id ?: 0,
    name = this.name ?: String(),
    lastName = this.lastName ?: String(),
    email = this.email ?: String()
)