package com.cibermodelo.cibermodeloapplication.views.deceasedInformation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.domain.usecase.GetFieldsByQueryTypeAndAccessLevelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeceasedInformationViewModel @Inject constructor(
    private val getFieldsByQueryTypeAndAccessLevelUseCase: GetFieldsByQueryTypeAndAccessLevelUseCase
) : ViewModel() {

    var fields by mutableStateOf(DeceasedInformationUiState(Resource.Loading()))

    fun getFieldsByQueryTypeAndAccessLevel(queryTypeId: Int, accessLevelId: Int) {
        viewModelScope.launch {
            getFieldsByQueryTypeAndAccessLevelUseCase(queryTypeId, accessLevelId).collect { response ->
                if(response is ResourceApi.Success) {
                    response.data?.let { data ->
                        fields = DeceasedInformationUiState(Resource.Success(data))
                    } ?: run {
                        fields = DeceasedInformationUiState(Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error"))
                    }
                } else {
                    fields = DeceasedInformationUiState(Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error"))
                }
            }
        }
    }

}