package com.cibermodelo.requestmanager.apiservice

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.User
import com.cibermodelo.data.apiservice.UserApiService
import com.cibermodelo.requestmanager.ApiService
import com.cibermodelo.requestmanager.mappers.toResource
import com.cibermodelo.requestmanager.mappers.toUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserApiServiceImpl @Inject constructor(
    private val service: ApiService,
) : UserApiService {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<ResourceApi<User>> {
        return flow {
            val response = service.login(email, password).toResource()
            if (response is ResourceApi.Success) {
                response.data?.let { data ->
                    emit(ResourceApi.Success(data.toUser()))
                } ?: run {
                    emit(
                        ResourceApi.Error(
                            errorCode = 1,
                            errorMessage = response.errorMessage ?: String()
                        )
                    )
                }
            } else {
                emit(
                    ResourceApi.Error(
                        errorCode = response.errorCode ?: 1,
                        errorMessage = response.errorMessage ?: String()
                    )
                )
            }

        }
    }

}