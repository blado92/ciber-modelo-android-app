package com.cibermodelo.cibermodeloapplication.views.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.User
import com.cibermodelo.domain.usecase.GetDeceasedByUserUseCase
import com.cibermodelo.domain.usecase.GetUserInformationUseCase
import com.cibermodelo.domain.usecase.RemoveLocalUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserInformationUseCase: GetUserInformationUseCase,
    private val getDeceasedByUserUseCase: GetDeceasedByUserUseCase,
    private val removeLocalUserUseCase: RemoveLocalUserUseCase
) : ViewModel() {

    var deceased by mutableStateOf(MainUiState(Resource.Loading()))

    var user by mutableStateOf(User())

    fun init() {
        viewModelScope.launch {
            getUserInformationUseCase().collect {
                user = it
            }
        }
    }

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

    fun logout() {
        viewModelScope.launch {
            removeLocalUserUseCase().collect()
        }
    }

}