package com.cibermodelo.domain.repository

import com.cibermodelo.base.model.User

interface UserRepository {
    suspend fun getUserFromDatabase() : User?
    suspend fun saveUserIntoDatabase(user: User) : Boolean
    suspend fun removeUserFromDatabase() : Boolean
}