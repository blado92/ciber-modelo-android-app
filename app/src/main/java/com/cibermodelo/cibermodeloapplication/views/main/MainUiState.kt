package com.cibermodelo.cibermodeloapplication.views.main

import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.model.Deceased

data class MainUiState(
    val content: Resource<List<Deceased>>
)