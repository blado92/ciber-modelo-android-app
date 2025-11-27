package com.cibermodelo.cibermodeloapplication.views.deceasedDocuments

import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.model.Document

data class DeceasedDocumentsUiState(
    val content: Resource<List<Document>>
)