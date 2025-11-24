package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.QueryTypes
import kotlinx.coroutines.flow.Flow

interface GetQueryTypesByUserAndDeceasedUseCase {
    suspend operator fun invoke(deceaseId: Int) : Flow<ResourceApi<List<QueryTypes>>>
}