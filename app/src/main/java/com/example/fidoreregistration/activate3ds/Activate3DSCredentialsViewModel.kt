package com.example.fidoreregistration.activate3ds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fidoreregistration.Event
import com.example.fidoreregistration.activate3ds.model.Credentials

class Activate3DSCredentialsViewModel : ViewModel() {

    private val _credentials = MutableLiveData<Credentials>()
    val credentials : LiveData<Credentials>
        get() = _credentials

    private val _confirmResult = MutableLiveData(Event(""))
    val confirmResult = _confirmResult

    init {
        _credentials.value = Credentials("maria83 / 123456789", "")
    }

    fun onPasswordEntered(password : String) {
        _credentials.value = _credentials.value?.copy(password = password)
    }

    fun confirm() {
        _confirmResult.value = Event("Confirmed")
    }
}