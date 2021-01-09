package com.example.fidoreregistration.questionnaire.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fidoreregistration.theme.pfiButtonHeight
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun QuestionScreen(
    screenDescription: QuestionScreenDescription,
    onPosBtnClick: () -> Unit,
    onNegBtnClick: () -> Unit,
    onExitClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val willGoBack = if (screenDescription.canGoBack) {
            onBackClick
        } else {
            null
        }
        val willExit = if (screenDescription.canExit) {
            onExitClick
        } else {
            null
        }
        TopBar(onBackClick = willGoBack, onExitClick = willExit)

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CoilImage(
                    data = screenDescription.imgUrl,
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .clipToBounds()
                )

                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    text = screenDescription.title,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = screenDescription.subtitle,
                    textAlign = TextAlign.Center
                )
            }
        }

        //end column
        screenDescription.negBtnText?.let {
            Text(
                text = it,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = onNegBtnClick)
                    .padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = onPosBtnClick,
            modifier = Modifier
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(50),
        ) {
            Text(text = screenDescription.posBtnText, modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun TopBar(
    onBackClick: (() -> Unit)?,
    onExitClick: (() -> Unit)?
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        val (back, exit) = createRefs()

        val canGoBack = onBackClick != null
        if (canGoBack) {
            IconButton(
                onClick = onBackClick!!,
                modifier = Modifier.constrainAs(back) {
                    start.linkTo(parent.start, 16.dp)
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack)
            }
        } else {
            Spacer(modifier = Modifier.size(16.dp))
        }

        val canExit = onExitClick != null
        if (canExit) {
            IconButton(
                onClick = onExitClick!!,
                modifier = Modifier.constrainAs(exit) {
                    end.linkTo(parent.end, 16.dp)
                }
            ) {
                Icon(imageVector = Icons.Default.Close)
            }
        } else {
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
fun QuestionScreenDefault() {
    val screenDescription = QuestionScreenDescription(
        title = "Thank you for downloading the PostFinance app",
        subtitle = "Do you already have an e-finance login?",
        negBtnText = "Not yet",
        posBtnText = "Yes, ready to go",
        canExit = true,
        canGoBack = true,
        imgUrl = QuestionScreenDescription.Img.HOUSE.url
    )
    QuestionScreen(
        screenDescription = screenDescription,
        onPosBtnClick = {},
        onNegBtnClick = {},
        onBackClick = {},
        onExitClick = {})
}

@Immutable
data class QuestionScreenDescription(
    val imgUrl: String,
    val title: String,
    val subtitle: String,
    val posBtnText: String,
    val negBtnText: String? = null,
    val canGoBack: Boolean = false,
    val canExit: Boolean = false
) {
    enum class Img(val url : String) {
        BALLOONS("https://s3-alpha-sig.figma.com/img/589b/4182/3fd9f1a31fec056d7f6bca5e9d10ce6a?Expires=1609718400&Signature=IlPQ9XLH5tWEaZ22zMcuud4U4bqLke2KeMOpzpnQlPlgZK-qptxBXqVq9r6V45zVYT8MQc88AAp2JjCGNNLU6EztCUIz8tRVkXADb9eoLPsTpWFkLXhmDOM84ku2kvQElB-VOdt9fJipyxuFlrK4yurNEV~tLPeFXzGNlGnVjuDo~aZ0FRkXrhgEcOBRz9IRcFM64uiZAqzjj7hpClTlEuGK6yxCSSf1hf8IftSAlKTYfCNgjX2-zBKeBv9AfMdm~JtRjaRnNszzY4IfQV3GmcNImUoAyk9t~wkeTXIKaw77wnbuzYyg98zZFECoWCHD5OvJXZdefKrV8nRQZktHPg__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"),
        HOUSE("https://s3-alpha-sig.figma.com/img/facb/d131/80ff1c6ed014ce70cf40e13b4b66a0a4?Expires=1609718400&Signature=Qn5Bjs1tK0ajWrSxP1VX3925Set4qaxvLh1KEAtXWT8V3u6Hx3OvE-2A9Tl9mVB3vcxCXOBNS1ksGCDyz68zOXZw5ISmh7-XI9RWYCvNjibVebCWtp87KounEs9-SmyG0n-ayyO-dlfKRWUV1NN2xmIraIvTv8VsXP0o6VPOx1U9Ec4yfAsQlwwrynpntQsUUzRYa00v9cNqHsGciIZ8V0kb1nrSgtuk-nT78x7yJ~uzzGvSABHYKBFqskl0jICLvMTE~7bXvLQ66by~Z-5rX02kib-PPmnrVTaf1gHtHZvWSald3D5MpHuNQVYaROPfRy~7oVK1Q0aCnPaq9CGBeQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"),
        PERMISSIONS("https://s3-alpha-sig.figma.com/img/c783/0ddd/2bbe2bffd313bca6130dee2930b7755e?Expires=1610323200&Signature=BV5CnDvgiSMhwO~CJ1iK3onx0bucVPIsQI1rMFATzBHtltK0nbitZqgSS0QGVudgSbFnFAe30Br1Wj2YOLsf1fzqXUfqN56xEedZCfTmWxnoBrAsBFtlSCfrEcGeRpw1e2j9c~y9B8IfjObHwRZnkzBPak3BHcXIEcaCJ9Xc9ZhyGx8bz50~QHgNqqy-IzWAHOg8CReuuxdM8pR1YUBw6mzE7CQ2lhTNY72JCpXnLJ2GDdniKaj67ES4NvJLo2bk7Na7a9ti1qwWzcrOVVYm58W32nBCfs9jEFl47-SzYq5YzSx~Ih-OHDJa8dhiNofcTBW9IviBLfbzFC3iOIka~w__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"),
        PADLOCK("https://s3-alpha-sig.figma.com/img/e1a7/e09d/f1d4d9094abbec80445dd26de1039e2f?Expires=1610323200&Signature=OJVKnD14KHeGlYewEwmoZVliPeTcHebqg4FHzZtGmn72TmegsxA-X6TNqd0DvGmrObQH9LW1KcFn8GK3X60L-bYWJ-z0WdnAs4Bx4tyBq6uUuIl2qojgn9t39Sn-Xh7oSAgMHvXJUt6ublE8DwQ~H-2WbS34uafWoLciur7xy5N~zKkxsVfAWOuBhYEf6~hztImj9ODtMXYZ3YpoLXqWeilBh9hKTOlLwgulc7UHYo5Bonzh1byCs1oN5TIH0uulxA84tnxJtB9Nu5--5wpYXUJlZgETVHfD~UdIHHBYPfHeoacgB8keSKpz2fnytcQ8Mbiw-Gw9kByhiDZnaylkgw__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA")
    }
}