package com.cibermodelo.data.apiservice

import com.cibermodelo.base.common.Resource
import com.cibermodelo.requestmanager.ApiService
import com.cibermodelo.requestmanager.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserApiServiceImpl @Inject constructor(
    private val service: ApiService,
) : UserApiService {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<UserResponse>> {
        return flow {
            val response = service.login(email, password)
            emit(Resource.Success(response))
        }
    }

}