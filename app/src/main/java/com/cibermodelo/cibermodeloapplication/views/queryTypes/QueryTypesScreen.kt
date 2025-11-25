package com.cibermodelo.cibermodeloapplication.views.queryTypes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.model.AccessLevel
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.QueryTypes
import com.cibermodelo.base.model.Role
import com.cibermodelo.cibermodeloapplication.R
import com.cibermodelo.cibermodeloapplication.common.LoadingMessage
import com.cibermodelo.cibermodeloapplication.ui.components.MainScreenEmptyContent

@Composable
fun QueryTypesScreen(
    modifier: Modifier,
    queryTypesUiState: QueryTypesUiState,
    deceased: Deceased,
    queryTypeSelected: (QueryTypes) -> Unit
) {
    when (val response = queryTypesUiState.content) {
        is Resource.Error<*> -> {
            MainScreenEmptyContent(stringResource(R.string.query_type_list_empty))
        }
        is Resource.Loading<*> -> {
            LoadingMessage()
        }
        is Resource.Success<*> -> {
            response.data?.let { data ->
                QueryTypesScreenContent(
                    modifier = modifier,
                    deceased = deceased,
                    content = data,
                    queryTypeSelected = queryTypeSelected
                )
            } ?: run {
                MainScreenEmptyContent(stringResource(R.string.query_type_list_empty))
            }
        }
    }
}

@Composable
private fun QueryTypeComponent(
    queryType: QueryTypes,
    queryTypeSelected: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .height(100.dp),
        colors = cardColors(White),
        elevation = cardElevation(6.dp),
        onClick = queryTypeSelected
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = 16.sp,
                color = Black,
                text = queryType.name
            )
        }
    }
}

@Composable
fun QueryTypesScreenContent(
    modifier: Modifier,
    deceased: Deceased,
    content: List<QueryTypes>,
    queryTypeSelected: (QueryTypes) -> Unit
) {
    Column(
        modifier = modifier
            .background(White)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            fontSize = 16.sp,
            color = Black,
            text = stringResource(R.string.query_type_deceased_label)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .height(100.dp),
            colors = cardColors(White),
            elevation = cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fontSize = 16.sp,
                    color = Black,
                    text = "${deceased.name} ${deceased.lastName}"
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 12.dp),
            fontSize = 16.sp,
            color = Black,
            text = stringResource(R.string.query_type_title)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp),
            verticalArrangement = spacedBy(8.dp),
        ) {
            itemsIndexed(content) { index, queryType ->
                QueryTypeComponent(queryType) {
                    queryTypeSelected(queryType)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun QueryTypesScreenContentPreview() {
    val deceased = Deceased(
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
    )
    val queryTypes = listOf(
        QueryTypes(
            id = 1,
            name = "Trámites Administrativos",
            role = Role(
                id = 1,
                name = "1er grado",
                description = String()
            ),
            accessLevel = AccessLevel(
                id = 3,
                name = "Nivel 3"
            )
        )
    )
    QueryTypesScreenContent(
        modifier = Modifier,
        deceased = deceased,
        content = queryTypes
    ) {

    }
}