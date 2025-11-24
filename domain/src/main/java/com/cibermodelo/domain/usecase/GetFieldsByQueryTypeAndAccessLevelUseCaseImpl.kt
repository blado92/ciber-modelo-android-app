package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Field
import com.cibermodelo.domain.repository.FieldRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetFieldsByQueryTypeAndAccessLevelUseCaseImpl constructor(
    private val fieldRepository: FieldRepository,
    private val dispatcher: CoroutineContext
) : GetFieldsByQueryTypeAndAccessLevelUseCase {

    override suspend fun invoke(
        queryTypeId: Int,
        accessLevelId: Int
    ): Flow<ResourceApi<List<Field>>> {
        return flow {
            fieldRepository.fieldByQueryTypeIdAndAccessLevel(queryTypeId, accessLevelId)
                .collect { response: ResourceApi<List<Field>> ->
                    emit(response)
                }
        }.flowOn(dispatcher)
    }
}