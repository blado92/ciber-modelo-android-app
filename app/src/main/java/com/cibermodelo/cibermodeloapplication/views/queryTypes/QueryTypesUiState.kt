package com.cibermodelo.cibermodeloapplication.views.queryTypes

import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.model.QueryTypes

data class QueryTypesUiState(
    val content: Resource<List<QueryTypes>>
)