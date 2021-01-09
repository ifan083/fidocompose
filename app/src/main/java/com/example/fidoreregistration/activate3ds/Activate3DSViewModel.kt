package com.example.fidoreregistration.activate3ds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fidoreregistration.Event
import com.example.fidoreregistration.activate3ds.model.Card
import com.example.fidoreregistration.activate3ds.model.SelectableCard
import java.util.*

class Activate3DSViewModel : ViewModel() {

    private val _selectableCards = MutableLiveData(listOf<SelectableCard>())
    val selectableCards = _selectableCards

    init {
        _selectableCards.value = cards().map { SelectableCard(it, false) }.toMutableList()
    }

    private val _activationResult = MutableLiveData(Event(""))
    val activationResult = _activationResult

    fun activateCards() {
        val activatedCardsString =
            _selectableCards.value?.filter { it.isSelected }?.joinToString { it.toString() }!!
        _activationResult.value = Event(activatedCardsString)
    }

    fun updateCardSelection(id: UUID, isSelected: Boolean) {
        val newList = _selectableCards.value?.map {
            if (it.card.id == id) {
                it.copy(isSelected = isSelected)
            } else {
                it
            }
        }
        _selectableCards.value = newList
    }

    private fun cards() = listOf(
        Card(
            number = "XXXX XXXX XXXX 4721",
            holder = "Maria Bernasconi",
            img = Card.CardImage.MasterCardSilver
        ),
        Card(
            number = "XXXX XXXX XXXX 4712",
            holder = "Maria Bernasconi",
            img = Card.CardImage.MasterCardGold
        ),
        Card(
            number = "XXXX XXXX XXXX 4713",
            holder = "Maria Bernasconi",
            img = Card.CardImage.MasterCardBlue
        )
    )
}