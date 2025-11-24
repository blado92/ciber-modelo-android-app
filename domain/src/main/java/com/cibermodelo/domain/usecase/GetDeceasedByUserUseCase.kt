package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import kotlinx.coroutines.flow.Flow

interface GetDeceasedByUserUseCase {
    suspend operator fun invoke() : Flow<ResourceApi<List<Deceased>>>
}