package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.domain.repository.DeceasedRepository
import com.cibermodelo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetDeceasedByUserUseCaseImpl constructor(
    private val deceasedRepository: DeceasedRepository,
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineContext
) : GetDeceasedByUserUseCase {
    override suspend fun invoke(): Flow<ResourceApi<List<Deceased>>> {
        return flow {
            val user = userRepository.getUserFromDatabase()
            deceasedRepository.deceasedByUser(user?.id ?: 0)
                .collect { response: ResourceApi<List<Deceased>> ->
                    emit(response)
                }
        }.flowOn(dispatcher)
    }
}