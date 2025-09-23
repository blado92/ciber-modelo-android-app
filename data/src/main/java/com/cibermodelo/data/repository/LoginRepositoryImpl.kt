package com.cibermodelo.data.repository

import com.cibermodelo.base.common.Resource
import com.cibermodelo.data.apiservice.UserApiService
import com.cibermodelo.data.mappers.toUser
import com.cibermodelo.domain.model.User
import com.cibermodelo.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val dispatcher: CoroutineContext,
) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<User>> {
        return flow {
            userApiService.login(email, password)
                .map { response ->
                    if(response is Resource.Success) {
                        return@map Resource.Success(
                            data = response.data?.toUser() ?: User()
                        )
                    } else {
                        return@map Resource.Error<User>(response.message ?: "")
                    }
                }
                .collect {
                    emit(it)
                }
        }.flowOn(dispatcher)
    }

}