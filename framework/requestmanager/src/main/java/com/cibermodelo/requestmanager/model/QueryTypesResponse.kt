package com.cibermodelo.requestmanager.model

data class QueryTypesResponse(
    val id: Int,
    val name: String,
    val role: RoleResponse,
    val accessLevel: AccessLevelResponse
)

data class RoleResponse(
    val id: Int,
    val name: String,
    val description: String
)

data class AccessLevelResponse(
    val id: Int,
    val name: String
)