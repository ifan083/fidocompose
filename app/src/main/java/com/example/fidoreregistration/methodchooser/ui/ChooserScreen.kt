package com.example.fidoreregistration.methodchooser.ui

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomDrawerLayout
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fidoreregistration.questionnaire.ui.TopBar

@Composable
fun Chooser(
    items: List<ItemData>,
    onItemClicked: (Int) -> Unit,
    onBackClick: () -> Unit,
    onExitClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(onBackClick = onBackClick, onExitClick = onExitClick)

        Text(
            text = "How can we check your account?",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 24.dp)
        )

        ScrollableColumn {
            items.forEach {
                ChooserItem(data = it, onItemClicked = onItemClicked)
            }
        }
    }
}

data class ItemData(
    val id: Int,
    val title: String,
    val subtitle: String,
    val img: ImageVector
)

@Composable
fun ChooserItem(
    data: ItemData,
    onItemClicked: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = { onItemClicked(data.id) })
            .padding(horizontal = 8.dp)
            .padding(top = 36.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {

            Icon(
                tint = Color.LightGray,
                imageVector = data.img.copy(defaultWidth = 45.dp, defaultHeight = 45.dp),
                modifier = Modifier.padding(end = 12.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = data.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Text(text = data.subtitle)
            }

            Icon(imageVector = Icons.Default.ArrowForwardIos)

        }

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
    }
}

@Preview
@Composable
fun ChooserItemPreview() {
    val data = ItemData(
        id = 1,
        title = "QR Code",
        subtitle = "You have received a QR activation code",
        img = Icons.Default.QrCode
    )
    ChooserItem(
        data = data,
        onItemClicked = {  /*TODO*/ }
    )
}

@Preview
@Composable
fun ChooserPreview() {
//    Chooser(onBackClick = { }, onExitClick = { })
}