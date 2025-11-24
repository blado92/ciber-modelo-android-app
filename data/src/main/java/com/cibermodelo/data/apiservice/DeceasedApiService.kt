package com.cibermodelo.data.apiservice

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import kotlinx.coroutines.flow.Flow

interface DeceasedApiService {
    suspend fun deceasedByUser(userId: Int) : Flow<ResourceApi<List<Deceased>>>
}