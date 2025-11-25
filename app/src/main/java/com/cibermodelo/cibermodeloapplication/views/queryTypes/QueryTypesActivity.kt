package com.cibermodelo.cibermodeloapplication.views.queryTypes

import android.content.Context
import android.content.Intent
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
import com.cibermodelo.base.model.QueryTypes
import com.cibermodelo.cibermodeloapplication.ui.theme.CiberModeloApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueryTypesActivity : ComponentActivity() {

    companion object {
        private const val DECEASED_INFORMATION = "deceasedInformation"

        fun getIntent(context: Context, deceased: Deceased) =
            Intent(context, QueryTypesActivity::class.java).apply {
                putExtra(DECEASED_INFORMATION, deceased)
            }
    }

    private val viewModel: QueryTypesViewModel by viewModels { defaultViewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val deceased = intent.getParcelableExtra(DECEASED_INFORMATION, Deceased::class.java)
        setContent {
            CiberModeloApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QueryTypesScreen(
                        modifier = Modifier.padding(innerPadding),
                        queryTypesUiState = viewModel.queryTypes,
                        deceased = deceased!!,
                        queryTypeSelected = { onQueryTypeSelected(it) }
                    )
                }
            }
        }

        viewModel.getQueryTypesByDeceased(deceased!!.id)
    }

    private fun onQueryTypeSelected(queryTypes: QueryTypes) {
        Toast.makeText(this, queryTypes.name, Toast.LENGTH_LONG).show()
    }

}