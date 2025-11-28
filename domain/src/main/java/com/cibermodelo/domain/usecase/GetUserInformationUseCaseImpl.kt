package com.cibermodelo.domain.usecase

import com.cibermodelo.base.model.User
import com.cibermodelo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetUserInformationUseCaseImpl constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineContext
) : GetUserInformationUseCase {
    override suspend fun invoke(): Flow<User> {
        return flow {
            val user = userRepository.getUserFromDatabase()
            emit(user ?: User())
        }.flowOn(dispatcher)
    }
}