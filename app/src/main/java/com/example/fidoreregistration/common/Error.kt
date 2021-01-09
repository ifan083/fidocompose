package com.example.fidoreregistration.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class ErrorLevel {
    Error,
    Info,
    Warning
}

@Composable
fun Error(
    errorLevel: ErrorLevel = ErrorLevel.Error,
    message: String,
    onClose: () -> Unit
) {
    val tint = when (errorLevel) {
        ErrorLevel.Error -> Color.Red
        ErrorLevel.Info -> Color.Blue
        ErrorLevel.Warning -> Color.DarkGray
    }
    Row(modifier = Modifier.padding(12.dp)) {
        IconButton(onClick = {  }) {
            Icon(imageVector = Icons.Default.Info, tint = tint)
        }

        Text(
            text = message,
            color = tint,
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp)
        )

        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Default.Close)
        }
    }
}