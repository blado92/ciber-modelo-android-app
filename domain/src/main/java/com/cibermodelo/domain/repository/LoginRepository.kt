package com.cibermodelo.domain.repository

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(email: String, password: String) : Flow<ResourceApi<User>>
}