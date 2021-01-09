package com.example.fidoreregistration.fido.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fidoreregistration.questionnaire.ui.EUser
import com.example.fidoreregistration.questionnaire.ui.TopBar
import com.example.fidoreregistration.theme.postYellow

@Composable
fun EUserSelection(
    openBottomSheet: () -> Unit,
    operationType: String = "Registration",
    onBack: () -> Unit,
    onExit: () -> Unit
) {
    val checkedState = remember { mutableStateOf(false) }
    val onCheckedChanged: (Boolean) -> Unit = { checkedState.value = it }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        TopBar(onBackClick = onBack, onExitClick = onExit)

        val text = "You set up $operationType for the following user."
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp)
        )

        EUserItem(
            EUser(),
            checked = checkedState.value,
            onCheckedChanged = onCheckedChanged
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            enabled = checkedState.value,
            shape = RoundedCornerShape(50),
            onClick = openBottomSheet,
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = "Register", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun EUserItem(
    eUser: EUser,
    checked: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = eUser.username)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(text = "E-Finance no: ")
                    Text(text = eUser.etn)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Switch(checked = checked, onCheckedChange = { onCheckedChanged(it) })
        }

        Divider(color = Color.LightGray)
    }
}

@Preview
@Composable
fun EUserItemPreview() {
    EUserItem(eUser = EUser(), checked = true, onCheckedChanged = {})
}

@Composable
fun BiometryDialog(
    closeBottomSheet: () -> Unit,
    onSuccess: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Activate user", fontWeight = FontWeight.Bold)

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fingerprintImgVector = Icons.Default.Fingerprint.copy(
                defaultWidth = 60.dp,
                defaultHeight = 60.dp
            )
            Icon(imageVector = fingerprintImgVector, tint = postYellow)
            Text(text = "Touch the fingerprint sensor")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = {
                onCancel()
                closeBottomSheet()
            }) {
                Text(text = "Cancel")
            }
            TextButton(onClick = {
                onSuccess()
                closeBottomSheet()
            }) {
                Text(text = "Authenticate")
            }
        }

    }
}

@Preview
@Composable
fun BiometryDialogPreview() {
    BiometryDialog(closeBottomSheet = {}, onSuccess = { /*TODO*/ }, onCancel = { /*TODO*/ })
}