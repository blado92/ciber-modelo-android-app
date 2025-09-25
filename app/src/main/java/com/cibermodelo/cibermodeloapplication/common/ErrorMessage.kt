package com.cibermodelo.cibermodeloapplication.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cibermodelo.cibermodeloapplication.R
import com.cibermodelo.cibermodeloapplication.ui.theme.CiberModeloApplicationTheme

@Composable
fun ErrorMessage(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.error_message)
        )
        Button(
            onClick = { onRetry() },
            content = {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorMessagePreview() {
    CiberModeloApplicationTheme {
        ErrorMessage(
            {

            }
        )
    }
}