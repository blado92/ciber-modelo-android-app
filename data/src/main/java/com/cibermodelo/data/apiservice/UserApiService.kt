package com.cibermodelo.data.apiservice

import com.cibermodelo.base.common.Resource
import com.cibermodelo.requestmanager.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserApiService {
    suspend fun login(email: String, password: String) : Flow<Resource<UserResponse>>
}