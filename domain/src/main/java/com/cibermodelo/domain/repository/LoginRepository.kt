package com.cibermodelo.domain.repository

import com.cibermodelo.base.common.Resource
import com.cibermodelo.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(email: String, password: String) : Flow<Resource<User>>
}