package com.cibermodelo.data.repository

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
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

}