package com.cibermodelo.data.repository

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Field
import com.cibermodelo.data.apiservice.FieldApiService
import com.cibermodelo.domain.repository.FieldRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FieldRepositoryImpl @Inject constructor(
    private val fieldApiService: FieldApiService,
    private val dispatcher: CoroutineContext,
) : FieldRepository {

    override suspend fun fieldByQueryTypeIdAndAccessLevel(
        queryTypeId: Int,
        accessLevelId: Int
    ): Flow<ResourceApi<List<Field>>> {
        return flow {
            fieldApiService.fieldByQueryTypeIdAndAccessLevel(queryTypeId, accessLevelId)
                .collect {
                    emit(it)
                }
        }.flowOn(dispatcher)
    }

}