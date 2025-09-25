package com.cibermodelo.cibermodeloapplication.views.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.cibermodelo.base.common.Resource
import com.cibermodelo.base.constants.ApiConstants.UNAUTHORIZED
import com.cibermodelo.base.model.User
import com.cibermodelo.base.utils.isValidEmail
import com.cibermodelo.cibermodeloapplication.R
import com.cibermodelo.cibermodeloapplication.common.InputTextBox
import com.cibermodelo.cibermodeloapplication.common.LoadingMessage
import com.cibermodelo.cibermodeloapplication.common.SimpleButton
import com.cibermodelo.cibermodeloapplication.ui.theme.CiberModeloApplicationTheme
import com.cibermodelo.cibermodeloapplication.ui.theme.darkPrimaryColor

@Composable
fun LoginScreen(
    login: LiveData<Resource<User?>?>,
    loginActions: (LoginActions) -> Unit
) {
    val uiState = login.observeAsState()
    LoginContent(
        uiState = uiState,
        loginActions = loginActions
    )
}

@Composable
private fun LoginContent(
    uiState: State<Resource<User?>?>? = null,
    loginActions: (LoginActions) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val isLoginEnabled = email.isNotEmpty() && email.isValidEmail() && password.isNotEmpty()

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(darkPrimaryColor),
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.mipmap.ic_launcher),
                contentDescription = "logo",
                modifier = Modifier
                    .width(250.dp)
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            InputTextBox(
                hint = stringResource(id = R.string.email),
                typePassword = false
            ) {
                email = it.text
            }
            InputTextBox(
                hint = stringResource(id = R.string.password),
                typePassword = true
            ) {
                password = it.text
            }
            SimpleButton(
                label = stringResource(id = R.string.login),
                isLoginEnabled
            ) {
                loginActions(LoginActions.OnLogin(email, password))
            }
            Text(
                text = stringResource(id = R.string.create_an_account),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 0.dp)
                    .clickable {
                        loginActions(LoginActions.OnCreateAccount)
                    },
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
            )
        }
        val context = LocalContext.current
        uiState?.value?.let { states ->
            when(states) {
                is Resource.Loading -> {
                    LoadingMessage()
                }
                is Resource.Success -> {
                    states.data?.let { data ->
                        loginActions(LoginActions.OnSuccess(data))
                    }
                }
                is Resource.Error -> {
                    LaunchedEffect(key1 = "errorMessage", block = {
                        val message = if(states.code == UNAUTHORIZED) {
                            context.getString(R.string.user_or_password_incorrect)
                        } else {
                            context.getString(R.string.error_message)
                        }
                        Toast.makeText(context, message, Toast.LENGTH_LONG)
                            .show()
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    CiberModeloApplicationTheme {
        LoginContent {

        }
    }
}