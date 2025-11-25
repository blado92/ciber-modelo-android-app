package com.cibermodelo.cibermodeloapplication.views.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Field
import com.cibermodelo.base.model.QueryTypes
import com.cibermodelo.domain.usecase.GetDeceasedByUserUseCase
import com.cibermodelo.domain.usecase.GetFieldsByQueryTypeAndAccessLevelUseCase
import com.cibermodelo.domain.usecase.GetQueryTypesByUserAndDeceasedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDeceasedByUserUseCase: GetDeceasedByUserUseCase,
    private val getFieldsByQueryTypeAndAccessLevelUseCase: GetFieldsByQueryTypeAndAccessLevelUseCase
) : ViewModel() {

    var deceased by mutableStateOf(MainUiState(Resource.Loading()))

    fun getDeceasedByUser() {
        viewModelScope.launch {
            getDeceasedByUserUseCase().collect { response ->
                if(response is ResourceApi.Success) {
                    response.data?.let { data ->
                        deceased = MainUiState(Resource.Success(data))
                    } ?: run {
                        deceased = MainUiState(Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error"))
                    }
                } else {
                    deceased = MainUiState(Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error"))
                }
            }
        }
    }



    private val _queryFields = MutableLiveData<Resource<List<Field>?>?>()
    val fields: LiveData<Resource<List<Field>?>?> = _queryFields

    fun getFieldsByQueryTypeAndAccessLevel(queryTypeId: Int, accessLevelId: Int) {
        _queryFields.value = Resource.Loading()
        viewModelScope.launch {
            getFieldsByQueryTypeAndAccessLevelUseCase(queryTypeId, accessLevelId).collect { response ->
                if(response is ResourceApi.Success) {
                    _queryFields.value = Resource.Success(response.data)
                } else {
                    _queryFields.value = Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error")
                }
            }
        }
    }

}