package com.cibermodelo.cibermodeloapplication.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.domain.usecase.GetDeceasedByUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDeceasedByUserUseCase: GetDeceasedByUserUseCase
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

}