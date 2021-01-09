package com.example.fidoreregistration.activate3ds.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fidoreregistration.activate3ds.model.Credentials
import com.example.fidoreregistration.questionnaire.ui.TopBar

@Composable
fun CredentialsScreen(
    credentials: State<Credentials?>,
    onBackClick: () -> Unit,
    onExitClick: () -> Unit,
    onConfirmClick: () -> Unit,
    onPasswordEntered: (String) -> Unit
) {
    ConstraintLayout {
        val (inputs, button) = createRefs()

        Column(modifier = Modifier.constrainAs(inputs) {
            top.linkTo(parent.top)
        }) {
            TopBar(onBackClick = onBackClick, onExitClick = onExitClick)

            Text(
                text = "Activate 3-D Secure",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(48.dp))

            credentials.value?.let { PfiText(text = it.username) }

            credentials.value?.let { PfiTextField(text = it.password, onTextChanged = onPasswordEntered) }

            Text(
                text = "Confirm the 3-D Secure activation with your e-finance password.",
                fontSize = 18.sp,
                modifier = Modifier.padding(12.dp)
            )
        }

        val enabled = credentials.value?.password?.isNotEmpty() ?: false
        Button(
            enabled = enabled,
            shape = RoundedCornerShape(50),
            onClick = onConfirmClick,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                    centerHorizontallyTo(parent)
                }) {
            Text(text = "Confirm", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun PfiTextField(
    text: String,
    onTextChanged: (String) -> Unit
) {
    val focusState = remember { mutableStateOf(FocusState.Inactive) }

    val dividerColor = when (focusState.value) {
        FocusState.Active -> MaterialTheme.colors.primary
        else -> Color.LightGray
    }

    val backgroundColor = MaterialTheme.colors.background

    PfiTextContainer(dividerColor = dividerColor) {

        Box(modifier = Modifier
            .weight(1f)
            .onFocusEvent { focusState.value = it }
        ) {
            TextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                backgroundColor = backgroundColor,
                activeColor = backgroundColor,
                inactiveColor = backgroundColor,
                errorColor = backgroundColor,
                value = text,
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = onTextChanged
            )
        }

        if (text.isNotEmpty()) {
            DeleteIcon(onDelete = { onTextChanged("") })
        }
    }
}

@Composable
fun PfiText(text: String) {
    PfiTextContainer {
        Text(
            text = text,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        )
    }
}

@Composable
private fun PfiTextContainer(
    dividerColor: Color = Color.LightGray,
    content: @Composable RowScope.() -> Unit,
) {
    val verticalPadding = 18.dp
    Column {
        Spacer(modifier = Modifier.height(verticalPadding))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }

    Spacer(modifier = Modifier.height(verticalPadding))
    Divider(color = dividerColor, modifier = Modifier.padding(horizontal = 8.dp))
}

@Composable
fun DeleteIcon(
    onDelete: () -> Unit
) {
    IconButton(onClick = onDelete) {
        Icon(imageVector = Icons.Default.Cancel)
    }
}

@Preview
@Composable
fun PfiTextFieldPreview() {
    PfiTextField(
        text = "mirko", onTextChanged = {}
    )
}