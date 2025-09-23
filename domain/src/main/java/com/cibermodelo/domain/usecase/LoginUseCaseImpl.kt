package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.Resource
import com.cibermodelo.domain.model.User
import com.cibermodelo.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class LoginUseCaseImpl(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineContext
) : LoginUseCase {

    override suspend fun invoke(
        email: String,
        password: String
    ): Flow<Resource<User>> {
        return flow {
            loginRepository.login(email, password)
                .collect { response: Resource<User> ->
                    emit(response)
                }
        }.flowOn(dispatcher)
    }

}