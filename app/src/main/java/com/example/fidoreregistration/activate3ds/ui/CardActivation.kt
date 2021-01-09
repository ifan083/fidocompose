package com.example.fidoreregistration.activate3ds.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fidoreregistration.activate3ds.model.SelectableCard
import com.example.fidoreregistration.questionnaire.ui.TopBar
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*

@Composable
fun CardActivationScreen(
    cards: State<List<SelectableCard>?>,
    onBackClick: () -> Unit,
    onExitClick: () -> Unit,
    onInfoClick: () -> Unit,
    onNextClick: () -> Unit,
    onNotNowClick: () -> Unit,
    onCardSelected: (UUID, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Online shopping - simple and secure",
                fontWeight = FontWeight.Bold
            )
            Text(text = "Activate 3-D Secure for the following cards.")
        }


        cards.value?.forEach { CardItem(it, onCardSelected) }

        MoreInfoItem(message = "What is 3-D Secure?", onClick = onInfoClick)

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onNotNowClick) {
            Text(text = "Not now")
        }

        Button(
            onClick = onNextClick,
            shape = RoundedCornerShape(50),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Next", modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp))
        }
    }
}

@Composable
fun MoreInfoItem(
    message: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp, horizontal = 8.dp)
        ) {
            Text(text = message, fontSize = 20.sp, modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowForwardIos)

        }
        Divider(color = Color.LightGray)
    }
}

@Composable
fun CardItem(
    selectableCard: SelectableCard,
    onCardSelected : (UUID, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoilImage(
                data = selectableCard.card.img.url,
                modifier = Modifier
                    .size(width = 80.dp, height = 40.dp)
                    .clipToBounds()
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = selectableCard.card.number, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = selectableCard.card.holder)
            }

            Switch(
                checked = selectableCard.isSelected,
                onCheckedChange = { onCardSelected(selectableCard.card.id, it) }
            )
        }

        Divider(color = Color.LightGray)
    }
}

@Preview
@Composable
fun CardItemPreview() {
    val card = SelectableCard(
        card = com.example.fidoreregistration.activate3ds.model.Card(
            number = "XXXX XXXX XXXX 4721",
            holder = "Maria Bernasconi",
            img = com.example.fidoreregistration.activate3ds.model.Card.CardImage.MasterCardBlue
        ),
        isSelected = true
    )

    CardItem(card) { _, _ ->  }
}
