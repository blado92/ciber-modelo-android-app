package com.cibermodelo.cibermodeloapplication.views.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.cibermodeloapplication.R
import com.cibermodelo.cibermodeloapplication.common.LoadingMessage
import com.cibermodelo.cibermodeloapplication.ui.components.GenericCardComponent
import com.cibermodelo.cibermodeloapplication.ui.components.MainScreenEmptyContent

@Composable
fun MainScreen(
    modifier: Modifier,
    mainUiState: MainUiState,
    deceasedSelected: (Deceased) -> Unit
) {
    when (val response = mainUiState.content) {
        is Resource.Error<*> -> {
            MainScreenEmptyContent(stringResource(R.string.deceased_list_empty))
        }
        is Resource.Loading<*> -> {
            LoadingMessage()
        }
        is Resource.Success<*> -> {
            response.data?.let { data ->
                MainScreenContent(
                    modifier = modifier,
                    content = data,
                    deceasedSelected = deceasedSelected
                )
            } ?: run {
                MainScreenEmptyContent(stringResource(R.string.deceased_list_empty))
            }
        }
    }
}

@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    content: List<Deceased>,
    deceasedSelected: (Deceased) -> Unit
) {
    Column(
        modifier = modifier
            .background(White)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            fontSize = 16.sp,
            text = stringResource(R.string.deceased_list_title),
            color = Black
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp),
            verticalArrangement = spacedBy(8.dp),
        ) {
            itemsIndexed(content) { index, deceased ->
                GenericCardComponent(
                    label = "${deceased.name} ${deceased.lastName}"
                ) {
                    deceasedSelected(deceased)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun MainScreenContentPreview() {
    val deceased = listOf(
        Deceased(
            id = 1,
            name = "Maria",
            lastName = "Pérez",
            email = "maria.perez@gmail.com",
            address = "miAddress",
            eps = "NuevaEps",
            birthday = "2025-11-07T00:00:00",
            deceasedDate = "2025-11-07T00:00:00",
            arl = String(),
            bankingInstitution = String(),
            identityDocument = 0,
            identityDocumentType = String(),
            maritalStatus = String(),
            nationality = String(),
            retirementFund = String(),
            severanceFund = String(),
            gender = String()
        ),
        Deceased(
            id = 2,
            name = "Carlos",
            lastName = "Pérez",
            email = "carlos.perez@gmail.com",
            address = "miAddress",
            eps = "NuevaEps",
            birthday = "2025-11-07T00:00:00",
            deceasedDate = "2025-11-07T00:00:00",
            arl = String(),
            bankingInstitution = String(),
            identityDocument = 0,
            identityDocumentType = String(),
            maritalStatus = String(),
            nationality = String(),
            retirementFund = String(),
            severanceFund = String(),
            gender = String()
        )
    )
    MainScreenContent(
        content = deceased
    ) {

    }
}