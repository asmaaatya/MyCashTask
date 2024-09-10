package com.mycash.ui

import androidx.lifecycle.ViewModel
import com.mycash.domain.model.login.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

class SharedViewModel : ViewModel() {
    private val _data = MutableStateFlow<Data?>(null)

    val userData: StateFlow<Data?> get() = _data
    fun setUserData(data:  Data) = runBlocking {
        _data.emit(data) }
}