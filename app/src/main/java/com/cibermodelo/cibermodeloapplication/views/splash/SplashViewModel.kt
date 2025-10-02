package com.cibermodelo.cibermodeloapplication.views.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.domain.usecase.IsUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : ViewModel() {

    private val _login = MutableLiveData<Resource<Boolean?>?>()
    val login: LiveData<Resource<Boolean?>?> = _login

    fun isUserLoggedIn() {
        viewModelScope.launch {
            isUserLoggedInUseCase().collect { response ->
                if(response) {
                    _login.value = Resource.Success(true)
                } else {
                    _login.value = Resource.Error(-1,"Error")
                }
            }
        }
    }

}