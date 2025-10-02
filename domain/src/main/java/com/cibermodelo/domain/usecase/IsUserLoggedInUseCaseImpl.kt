package com.cibermodelo.domain.usecase

import com.cibermodelo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class IsUserLoggedInUseCaseImpl constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineContext
) : IsUserLoggedInUseCase {

    override suspend fun invoke(): Flow<Boolean> {
        return flow {
            val user = userRepository.getUserFromDatabase()
            emit(user != null)
        }.flowOn(dispatcher)
    }

}