package com.cibermodelo.domain.repository

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Field
import kotlinx.coroutines.flow.Flow

interface FieldRepository {
    suspend fun fieldByQueryTypeIdAndAccessLevel(queryTypeId: Int, accessLevelId: Int) : Flow<ResourceApi<List<Field>>>
}