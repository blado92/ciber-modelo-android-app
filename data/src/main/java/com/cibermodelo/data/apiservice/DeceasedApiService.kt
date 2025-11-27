package com.cibermodelo.data.apiservice

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.Document
import com.cibermodelo.base.model.QueryTypes
import kotlinx.coroutines.flow.Flow

interface DeceasedApiService {
    suspend fun deceasedByUser(userId: Int) : Flow<ResourceApi<List<Deceased>>>
    suspend fun queryTypesByUserAndDeceased(userId: Int, deceased: Int) : Flow<ResourceApi<List<QueryTypes>>>
    suspend fun documentsByDeceased(deceasedId: Int) : Flow<ResourceApi<List<Document>>>
}