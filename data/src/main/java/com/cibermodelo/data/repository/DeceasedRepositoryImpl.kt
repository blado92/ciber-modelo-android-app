package com.cibermodelo.data.repository

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.Document
import com.cibermodelo.base.model.QueryTypes
import com.cibermodelo.data.apiservice.DeceasedApiService
import com.cibermodelo.domain.repository.DeceasedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DeceasedRepositoryImpl @Inject constructor(
    private val deceasedApiService: DeceasedApiService,
    private val dispatcher: CoroutineContext,
) : DeceasedRepository {

    override suspend fun deceasedByUser(userId: Int): Flow<ResourceApi<List<Deceased>>> {
        return flow {
            deceasedApiService.deceasedByUser(userId)
                .collect {
                    emit(it)
                }
        }.flowOn(dispatcher)
    }

    override suspend fun queryTypesByUserAndDeceased(
        userId: Int,
        deceased: Int
    ): Flow<ResourceApi<List<QueryTypes>>> {
        return flow {
            deceasedApiService.queryTypesByUserAndDeceased(userId, deceased)
                .collect {
                    emit(it)
                }
        }.flowOn(dispatcher)
    }

    override suspend fun documentsByDeceased(deceasedId: Int): Flow<ResourceApi<List<Document>>> {
        return flow {
            deceasedApiService.documentsByDeceased(deceasedId)
                .collect {
                    emit(it)
                }
        }.flowOn(dispatcher)
    }

}