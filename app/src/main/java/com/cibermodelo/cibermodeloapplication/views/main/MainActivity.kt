package com.cibermodelo.cibermodeloapplication.views.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.cibermodeloapplication.ui.theme.CiberModeloApplicationTheme
import com.cibermodelo.cibermodeloapplication.views.login.LoginActivity
import com.cibermodelo.cibermodeloapplication.views.queryTypes.QueryTypesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { defaultViewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.init()
        setContent {
            CiberModeloApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        mainUiState = viewModel.deceased,
                        deceasedSelected = { onDeceasedSelected(it) },
                        user = viewModel.user,
                        onLogout = { onLogout() }
                    )
                }
            }
        }

        viewModel.getDeceasedByUser()
    }

    private fun onLogout() {
        viewModel.logout()
        startActivity(
            Intent(this, LoginActivity::class.java)
        )
        finish()
    }

    private fun onDeceasedSelected(deceased: Deceased) {
        startActivity(
            QueryTypesActivity.getIntent(this, deceased)
        )
    }
}