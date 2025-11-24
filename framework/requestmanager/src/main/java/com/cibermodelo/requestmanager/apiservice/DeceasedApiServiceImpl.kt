package com.cibermodelo.requestmanager.apiservice

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.data.apiservice.DeceasedApiService
import com.cibermodelo.requestmanager.ApiService
import com.cibermodelo.requestmanager.mappers.toDeceased
import com.cibermodelo.requestmanager.mappers.toDeceasedList
import com.cibermodelo.requestmanager.mappers.toResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeceasedApiServiceImpl @Inject constructor(
    private val service: ApiService,
) : DeceasedApiService {

    override suspend fun deceasedByUser(userId: Int): Flow<ResourceApi<List<Deceased>>> {
        return flow {
            try {
                val response = service.deceasedByUser(userId).toResource()
                if (response is ResourceApi.Success) {
                    response.data?.let { data ->
                        emit(ResourceApi.Success(data.toDeceasedList()))
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