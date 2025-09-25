package com.cibermodelo.data.apiservice

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.User
import kotlinx.coroutines.flow.Flow

interface UserApiService {
    suspend fun login(email: String, password: String) : Flow<ResourceApi<User>>
}