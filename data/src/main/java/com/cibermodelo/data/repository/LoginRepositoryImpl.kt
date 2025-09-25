package com.cibermodelo.data.repository

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.User
import com.cibermodelo.data.apiservice.UserApiService
import com.cibermodelo.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val dispatcher: CoroutineContext,
) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<ResourceApi<User>> {
        return flow {
            userApiService.login(email, password)
                .collect {
                    emit(it)
                }
        }.flowOn(dispatcher)
    }

}