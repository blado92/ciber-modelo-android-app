package com.cibermodelo.cibermodeloapplication.views.main

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
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.cibermodeloapplication.R
import com.cibermodelo.cibermodeloapplication.common.LoadingMessage

@Composable
fun MainScreen(
    modifier: Modifier,
    mainUiState: MainUiState,
    deceasedSelected: (Deceased) -> Unit
) {
    when (val response = mainUiState.content) {
        is Resource.Error<*> -> {
            MainScreenEmptyContent()
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
                MainScreenEmptyContent()
            }
        }
    }
}

@Composable
private fun DeceasedComponent(
    deceased: Deceased,
    deceasedSelected: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .height(100.dp),
        colors = cardColors(White),
        elevation = cardElevation(6.dp),
        onClick = deceasedSelected
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
                DeceasedComponent(deceased) {
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
            deceasedDate = "2025-11-07T00:00:00"
        ),
        Deceased(
            id = 2,
            name = "Carlos",
            lastName = "Pérez",
            email = "carlos.perez@gmail.com",
            address = "miAddress",
            eps = "NuevaEps",
            birthday = "2025-11-07T00:00:00",
            deceasedDate = "2025-11-07T00:00:00"
        )
    )
    MainScreenContent(
        content = deceased
    ) {

    }
}

@Composable
private fun MainScreenEmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(White)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp),
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
                    text = stringResource(R.string.deceased_list_empty),
                    color = Black
                )
            }
        }
    }
}