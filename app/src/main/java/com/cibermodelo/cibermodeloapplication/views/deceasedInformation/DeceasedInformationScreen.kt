package com.cibermodelo.cibermodeloapplication.views.deceasedInformation

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
import com.cibermodelo.base.model.Field
import com.cibermodelo.base.model.FieldType
import com.cibermodelo.cibermodeloapplication.R
import com.cibermodelo.cibermodeloapplication.common.LoadingMessage
import com.cibermodelo.cibermodeloapplication.ui.components.MainScreenEmptyContent

@Composable
fun DeceasedInformationScreen(
    modifier: Modifier,
    deceasedInformationUiState: DeceasedInformationUiState,
    deceased: Deceased
) {
    when (val response = deceasedInformationUiState.content) {
        is Resource.Error<*> -> {
            MainScreenEmptyContent(stringResource(R.string.deceased_information_empty))
        }
        is Resource.Loading<*> -> {
            LoadingMessage()
        }
        is Resource.Success<*> -> {
            response.data?.let { data ->
                DeceasedInformationScreenContent(
                    modifier = modifier,
                    deceased = deceased,
                    content = data
                )
            } ?: run {
                MainScreenEmptyContent(stringResource(R.string.deceased_information_empty))
            }
        }
    }
}

@Composable
fun DeceasedInformationScreenContent(
    modifier: Modifier,
    deceased: Deceased,
    content: List<Field>
) {
    Column(
        modifier = modifier
            .background(White)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 12.dp),
            fontSize = 16.sp,
            color = Black,
            text = stringResource(R.string.deceased_information_title)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp),
            verticalArrangement = spacedBy(8.dp),
        ) {
            itemsIndexed(content) { index, field ->
                GetFieldDeceasedComponent(
                    deceased,
                    field
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun DeceasedInformationScreenContentPreview() {
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

    val fields = listOf(
        Field(
            FieldType.DECEASED_ADDRESS,
            "Direccion",
        ),
        Field(
            FieldType.DECEASED_BIRTHDAY,
            "Fecha Cumpleaños"
        ),
        Field(
            FieldType.DECEASED_EMAIL,
            "Correo Electronico"
        ),
        Field(
            FieldType.DECEASED_EPS,
            "EPS"
        ),
        Field(
            FieldType.DESEASED_DECEASEDDATE,
            "Fecha Fallecimiento"
        )
    )

    DeceasedInformationScreenContent(
        modifier = Modifier,
        deceased = deceased,
        content = fields
    )
}
