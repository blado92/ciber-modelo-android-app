package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Document
import kotlinx.coroutines.flow.Flow

interface GetDocumentsByDeceasedIdUseCase {
    suspend operator fun invoke(deceasedId: Int) : Flow<ResourceApi<List<Document>>>
}