package com.cibermodelo.cibermodeloapplication.views.deceasedInformation

import android.content.Context
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeceasedInformationActivity : ComponentActivity() {

    companion object {
        private const val DECEASED_INFORMATION = "deceasedInformation"
        private const val QUERY_TYPE_ID = "queryTypeId"
        private const val ACCESS_LEVEL_ID = "accessLevelId"

        fun getIntent(context: Context, deceased: Deceased, queryTypeId: Int, accessLevelId: Int) =
            Intent(context, DeceasedInformationActivity::class.java).apply {
                putExtra(DECEASED_INFORMATION, deceased)
                putExtra(QUERY_TYPE_ID, queryTypeId)
                putExtra(ACCESS_LEVEL_ID, accessLevelId)
            }
    }

    private val viewModel: DeceasedInformationViewModel by viewModels { defaultViewModelProviderFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val deceased = intent.getParcelableExtra(DECEASED_INFORMATION, Deceased::class.java)
        setContent {
            CiberModeloApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DeceasedInformationScreen(
                        modifier = Modifier.padding(innerPadding),
                        deceasedInformationUiState = viewModel.fields,
                        deceased = deceased!!
                    )
                }
            }
        }

        val queryTypeId = intent.getIntExtra(QUERY_TYPE_ID, 0)
        val accessLevelId = intent.getIntExtra(ACCESS_LEVEL_ID, 0)
        viewModel.getFieldsByQueryTypeAndAccessLevel(queryTypeId = queryTypeId, accessLevelId = accessLevelId)
    }

}