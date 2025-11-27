package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Document
import com.cibermodelo.domain.repository.DeceasedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetDocumentsByDeceasedIdUseCaseImpl constructor(
    private val deceasedRepository: DeceasedRepository,
    private val dispatcher: CoroutineContext
) : GetDocumentsByDeceasedIdUseCase {
    override suspend fun invoke(deceasedId: Int): Flow<ResourceApi<List<Document>>> {
        return flow {
            deceasedRepository.documentsByDeceased(deceasedId)
                .collect { response: ResourceApi<List<Document>> ->
                    emit(response)
                }
        }.flowOn(dispatcher)
    }

}