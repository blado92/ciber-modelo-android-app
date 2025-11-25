package com.cibermodelo.cibermodeloapplication.views.queryTypes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.domain.usecase.GetQueryTypesByUserAndDeceasedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QueryTypesViewModel @Inject constructor(
    private val getQueryTypesByUserAndDeceasedUseCase: GetQueryTypesByUserAndDeceasedUseCase
) : ViewModel() {

    var queryTypes by mutableStateOf(QueryTypesUiState(Resource.Loading()))

    fun getQueryTypesByDeceased(deceased: Int) {
        viewModelScope.launch {
            getQueryTypesByUserAndDeceasedUseCase(deceased).collect { response ->
                if(response is ResourceApi.Success) {
                    response.data?.let { data ->
                        queryTypes = QueryTypesUiState(Resource.Success(data))
                    } ?: run {
                        queryTypes = QueryTypesUiState(Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error"))
                    }
                } else {
                    queryTypes = QueryTypesUiState(Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error"))
                }
            }
        }
    }

}