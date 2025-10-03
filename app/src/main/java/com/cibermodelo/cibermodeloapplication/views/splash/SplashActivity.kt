package com.cibermodelo.cibermodeloapplication.views.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cibermodelo.base.common.Resource
import com.cibermodelo.cibermodeloapplication.views.login.LoginActivity
import com.cibermodelo.cibermodeloapplication.views.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    private var keepSplash = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        validateUser()

        installSplashScreen().setKeepOnScreenCondition { keepSplash }
    }

    private fun validateUser() {
        viewModel.login.observe(this) { response ->
            if (response is Resource.Success) {
                goToHome()
            } else {
                goToLogin()
            }
            keepSplash = false
        }
        viewModel.isUserLoggedIn()
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}