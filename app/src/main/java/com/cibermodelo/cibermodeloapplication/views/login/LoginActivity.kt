package com.cibermodelo.cibermodeloapplication.views.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.cibermodelo.cibermodeloapplication.MainActivity
import com.cibermodelo.cibermodeloapplication.ui.theme.CiberModeloApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels { defaultViewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CiberModeloApplicationTheme {
                LoginScreen(
                    login = viewModel.login,
                    loginActions = { onLoginActions(it) }
                )
            }
        }
    }

    private fun onLoginActions(loginActions: LoginActions) {
        when (loginActions) {
            is LoginActions.OnCreateAccount -> {

            }
            is LoginActions.OnLogin -> {
                viewModel.loginUser(loginActions.email, loginActions.password)
            }
            is LoginActions.OnSuccess -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

}