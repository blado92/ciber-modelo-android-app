package com.cibermodelo.cibermodeloapplication.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
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
    private val getQueryTypesByUserAndDeceasedUseCase: GetQueryTypesByUserAndDeceasedUseCase,
    private val getFieldsByQueryTypeAndAccessLevelUseCase: GetFieldsByQueryTypeAndAccessLevelUseCase
) : ViewModel() {

    private val _deceased = MutableLiveData<Resource<List<Deceased>?>?>()
    val deceased: LiveData<Resource<List<Deceased>?>?> = _deceased

    fun getDeceasedByUser() {
        _deceased.value = Resource.Loading()
        viewModelScope.launch {
            getDeceasedByUserUseCase().collect { response ->
                if(response is ResourceApi.Success) {
                    _deceased.value = Resource.Success(response.data)
                } else {
                    _deceased.value = Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error")
                }
            }
        }
    }

    private val _queryTypes = MutableLiveData<Resource<List<QueryTypes>?>?>()
    val queryTypes: LiveData<Resource<List<QueryTypes>?>?> = _queryTypes

    fun getQueryTypesByDeceased(deceased: Int) {
        _queryTypes.value = Resource.Loading()
        viewModelScope.launch {
            getQueryTypesByUserAndDeceasedUseCase(deceased).collect { response ->
                if(response is ResourceApi.Success) {
                    _queryTypes.value = Resource.Success(response.data)
                } else {
                    _queryTypes.value = Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error")
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