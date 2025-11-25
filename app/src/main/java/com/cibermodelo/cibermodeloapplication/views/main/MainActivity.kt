package com.cibermodelo.cibermodeloapplication.views.main

import android.os.Bundle
import android.widget.Toast
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { defaultViewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CiberModeloApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        mainUiState = viewModel.deceased,
                        deceasedSelected = { onDeceasedSelected(it) }
                    )
                }
            }
        }

        viewModel.getDeceasedByUser()
    }

    private fun onDeceasedSelected(deceased: Deceased) {
        Toast.makeText(this, deceased.name, Toast.LENGTH_LONG).show()
    }
}