package com.example.fidoreregistration.activate3ds.model

import java.util.*

data class Card(
    val number: String,
    val holder: String,
    val img: CardImage,
    val id : UUID = UUID.randomUUID()
) {

    enum class CardImage(val url: String) {
        MasterCardSilver("https://www.cimbanque.com/cim/images/cards/mastercard/mastercard-silver-250.png"),
        MasterCardGold("https://www.cimbanque.com/cim/images/cards/mastercard/mastercard-gold-250.png"),
        MasterCardBlue("https://www.cimbanque.com/cim/images/cards/mastercard/mastercard-flying-blue-250.png")
    }
}

data class SelectableCard(
    val card: Card,
    var isSelected: Boolean
)

