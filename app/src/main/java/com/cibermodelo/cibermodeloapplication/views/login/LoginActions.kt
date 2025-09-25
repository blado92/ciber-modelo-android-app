package com.cibermodelo.cibermodeloapplication.views.login

import com.cibermodelo.base.model.User

sealed class LoginActions {
    data class OnLogin(val email: String, val password: String) : LoginActions()
    data class OnSuccess(val user: User) : LoginActions()
    data object OnCreateAccount : LoginActions()
}
