package com.cibermodelo.domain.repository

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import kotlinx.coroutines.flow.Flow

interface DeceasedRepository {
    suspend fun deceasedByUser(userId: Int) : Flow<ResourceApi<List<Deceased>>>
}