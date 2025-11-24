package com.cibermodelo.cibermodeloapplication.views.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.User
import com.cibermodelo.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUseCase
) : ViewModel() {

    private val _login = MutableLiveData<Resource<User?>?>()
    val login: LiveData<Resource<User?>?> = _login

    fun loginUser(email: String, password: String) {
        _login.value = Resource.Loading()
        viewModelScope.launch {

            loginUserUseCase(email, password).collect { response ->
                if(response is ResourceApi.Success) {
                    _login.value = Resource.Success(response.data)
                } else {
                    _login.value = Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error")
                }
            }
        }
    }

}