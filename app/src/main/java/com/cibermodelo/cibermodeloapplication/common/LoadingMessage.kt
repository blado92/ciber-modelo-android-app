package com.cibermodelo.cibermodeloapplication.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cibermodelo.cibermodeloapplication.ui.theme.CiberModeloApplicationTheme

@Composable
fun LoadingMessage() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingMessagePreview() {
    CiberModeloApplicationTheme {
        LoadingMessage()
    }
}