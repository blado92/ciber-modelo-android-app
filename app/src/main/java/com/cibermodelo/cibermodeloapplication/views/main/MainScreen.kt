package com.cibermodelo.cibermodeloapplication.views.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.model.Deceased
import com.cibermodelo.base.model.User
import com.cibermodelo.cibermodeloapplication.R
import com.cibermodelo.cibermodeloapplication.common.LoadingMessage
import com.cibermodelo.cibermodeloapplication.ui.components.EmptyComponent
import com.cibermodelo.cibermodeloapplication.ui.components.GenericCardComponent
import com.cibermodelo.cibermodeloapplication.views.login.LoginActions

@Composable
fun MainScreen(
    modifier: Modifier,
    mainUiState: MainUiState,
    user: User,
    deceasedSelected: (Deceased) -> Unit,
    onLogout: () -> Unit
) {
    when (val response = mainUiState.content) {
        is Resource.Error<*> -> {
            MainScreenEmptyContent(modifier, user, onLogout)
        }

        is Resource.Loading<*> -> {
            LoadingMessage()
        }

        is Resource.Success<*> -> {
            response.data?.let { data ->
                MainScreenContent(
                    modifier = modifier,
                    content = data,
                    deceasedSelected = deceasedSelected,
                    user = user,
                    onLogout = onLogout
                )
            } ?: run {
                MainScreenEmptyContent(modifier, user, onLogout)
            }
        }
    }
}

@Composable
private fun MainScreenEmptyContent(
    modifier: Modifier = Modifier,
    user: User,
    onLogout: () -> Unit
) {
    Column(
        modifier = modifier
            .background(White)
            .fillMaxSize()
            .padding(16.dp)
            .then(modifier)
    ) {
        MainScreenUserComponent(
            modifier = Modifier.padding(bottom = 16.dp),
            user = user,
            onLogout = onLogout
        )

        EmptyComponent(
            modifier = Modifier.padding(16.dp),
            label = stringResource(R.string.deceased_list_empty)
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun MainScreenEmptyContentPreview() {
    val user = User(
        name = "Bladimir",
        lastName = "Alvarez"
    )
    MainScreenEmptyContent(
        user = user
    ) {

    }
}

@Composable
private fun MainScreenUserComponent(
    modifier: Modifier = Modifier,
    user: User,
    onLogout: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            fontSize = 16.sp,
            text = stringResource(R.string.user_information_title),
            color = Black
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
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
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        fontSize = 16.sp,
                        color = Black,
                        text = "${user.name} ${user.lastName}"
                    )
                    Image(
                        modifier = Modifier.size(30.dp).padding(start = 8.dp).clickable {
                            onLogout()
                        },
                        painter = painterResource(R.drawable.ic_logout),
                        contentDescription = String()
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    content: List<Deceased>,
    user: User,
    deceasedSelected: (Deceased) -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = modifier
            .background(White)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MainScreenUserComponent(
            user = user,
            onLogout = onLogout
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 16.sp,
            text = stringResource(R.string.deceased_list_title),
            color = Black
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
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
    val user = User(
        name = "Bladimir",
        lastName = "Alvarez"
    )
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
        content = deceased,
        user = user,
        deceasedSelected = {  },
        onLogout = {  }
    )
}