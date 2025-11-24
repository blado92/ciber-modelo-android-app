package com.cibermodelo.base.model

data class QueryTypes(
    val id: Int,
    val name: String,
    val role: Role,
    val accessLevel: AccessLevel
)
