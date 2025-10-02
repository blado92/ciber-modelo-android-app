package com.cibermodelo.data.repository

import com.cibermodelo.base.model.User
import com.cibermodelo.databasemanager.daos.UserDao
import com.cibermodelo.databasemanager.mappers.toUser
import com.cibermodelo.databasemanager.mappers.toUserEntity
import com.cibermodelo.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
): UserRepository {

    override suspend fun getUserFromDatabase(): User? {
        val user = userDao.selectUser()
        return user.firstOrNull()?.toUser()
    }

    override suspend fun saveUserIntoDatabase(user: User): Boolean {
        userDao.insertUser(listOf(user.toUserEntity()))
        return true
    }

    override suspend fun removeUserFromDatabase(): Boolean {
        userDao.deleteUser()
        return true
    }

}