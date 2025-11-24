package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.QueryTypes
import com.cibermodelo.domain.repository.DeceasedRepository
import com.cibermodelo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetQueryTypesByUserAndDeceasedUseCaseImpl constructor(
    private val deceasedRepository: DeceasedRepository,
    private val userRepository: UserRepository,
    private val dispatcher: CoroutineContext
) : GetQueryTypesByUserAndDeceasedUseCase {
    override suspend fun invoke(deceaseId: Int): Flow<ResourceApi<List<QueryTypes>>> {
        return flow {
            val user = userRepository.getUserFromDatabase()
            deceasedRepository.queryTypesByUserAndDeceased(user?.id ?: 0, deceaseId)
                .collect { response: ResourceApi<List<QueryTypes>> ->
                    emit(response)
                }
        }.flowOn(dispatcher)
    }
}