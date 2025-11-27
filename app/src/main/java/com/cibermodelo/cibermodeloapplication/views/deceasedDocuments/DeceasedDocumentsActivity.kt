package com.cibermodelo.cibermodeloapplication.views.deceasedDocuments

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
import androidx.core.net.toUri
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.Document
import com.cibermodelo.cibermodeloapplication.ui.theme.CiberModeloApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeceasedDocumentsActivity : ComponentActivity() {

    companion object {
        private const val DECEASED_INFORMATION = "deceasedInformation"

        fun getIntent(context: Context, deceased: Deceased) =
            Intent(context, DeceasedDocumentsActivity::class.java).apply {
                putExtra(DECEASED_INFORMATION, deceased)
            }
    }

    private val viewModel: DeceasedDocumentsViewModel by viewModels { defaultViewModelProviderFactory }
    private lateinit var deceased: Deceased

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        deceased = intent.getParcelableExtra(DECEASED_INFORMATION, Deceased::class.java)!!
        setContent {
            CiberModeloApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DeceasedDocumentsScreen(
                        modifier = Modifier.padding(innerPadding),
                        deceasedDocumentsUiState = viewModel.documents,
                        deceased = deceased,
                        documentSelected = { onDocumentSelected(it) }
                    )
                }
            }
        }

        viewModel.getDocuments(deceased.id)
    }

    private fun onDocumentSelected(document: Document) {
        val intent = Intent(Intent.ACTION_VIEW, document.url.toUri())
        startActivity(intent)
    }

}