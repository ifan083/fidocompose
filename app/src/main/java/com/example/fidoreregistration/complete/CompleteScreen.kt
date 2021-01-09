package com.example.fidoreregistration.complete

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fidoreregistration.questionnaire.ui.TopBar

@Composable
fun CompleteScreen(
    title: String,
    subtitle: String,
    onCompleteClick: () -> Unit,
    onExit: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onBackClick = null, onExitClick = onExit)

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //icon
                val icon = Icons.Default.AddTask.copy(
                    defaultWidth = 80.dp,
                    defaultHeight = 80.dp
                )
                Icon(imageVector = icon)
                Spacer(modifier = Modifier.height(12.dp))

                //bold text
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
                )

                //normal text
                Text(
                    text = subtitle,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )

            }
        }

        OutlinedButton(
            onClick = onCompleteClick,
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "Close", modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}