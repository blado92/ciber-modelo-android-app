package com.cibermodelo.cibermodeloapplication.views.deceasedDocuments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.domain.usecase.GetDocumentsByDeceasedIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeceasedDocumentsViewModel @Inject constructor(
    private val getDocumentsByDeceasedIdUseCase: GetDocumentsByDeceasedIdUseCase
) : ViewModel() {

    var documents by mutableStateOf(DeceasedDocumentsUiState(Resource.Loading()))

    fun getDocuments(deceasedId: Int) {
        viewModelScope.launch {
            getDocumentsByDeceasedIdUseCase(deceasedId).collect { response ->
                if(response is ResourceApi.Success) {
                    response.data?.let { data ->
                        documents = DeceasedDocumentsUiState(Resource.Success(data))
                    } ?: run {
                        documents = DeceasedDocumentsUiState(Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error"))
                    }
                } else {
                    documents = DeceasedDocumentsUiState(Resource.Error(response.errorCode ?: 1, response.errorMessage ?: "Error"))
                }
            }
        }
    }

}