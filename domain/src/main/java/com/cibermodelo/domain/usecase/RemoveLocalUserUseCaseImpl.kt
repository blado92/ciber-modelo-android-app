package com.cibermodelo.domain.usecase

import com.cibermodelo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class RemoveLocalUserUseCaseImpl constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineContext
) : RemoveLocalUserUseCase {
    override suspend fun invoke(): Flow<Boolean> {
        return flow {
            userRepository.removeUserFromDatabase()
            emit(true)
        }.flowOn(dispatcher)
    }
}