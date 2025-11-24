package com.cibermodelo.requestmanager.apiservice

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Field
import com.cibermodelo.data.apiservice.FieldApiService
import com.cibermodelo.requestmanager.ApiService
import com.cibermodelo.requestmanager.mappers.toFieldList
import com.cibermodelo.requestmanager.mappers.toResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FieldApiServiceImpl @Inject constructor(
    private val service: ApiService,
) : FieldApiService {

    override suspend fun fieldByQueryTypeIdAndAccessLevel(
        queryTypeId: Int,
        accessLevelId: Int
    ): Flow<ResourceApi<List<Field>>> {
        return flow {
            try {
                val response = service.fieldByQueryTypeIdAndAccessLevel(queryTypeId, accessLevelId).toResource()
                if (response is ResourceApi.Success) {
                    response.data?.let { data ->
                        emit(ResourceApi.Success(data.toFieldList()))
                    } ?: run {
                        emit(
                            ResourceApi.Error(
                                errorCode = 1,
                                errorMessage = response.errorMessage ?: String()
                            )
                        )
                    }
                } else {
                    emit(
                        ResourceApi.Error(
                            errorCode = response.errorCode ?: 1,
                            errorMessage = response.errorMessage ?: String()
                        )
                    )
                }
            } catch (ex: Exception) {
                emit(
                    ResourceApi.Error(
                        errorCode = 1,
                        errorMessage = ex.message ?: String()
                    )
                )
            }
        }
    }

}