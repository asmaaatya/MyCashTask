package com.mycash.ui

import androidx.lifecycle.ViewModel
import com.mycash.domain.models.beans.login.Login
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

class SharedViewModel : ViewModel() {
    private val _login = MutableStateFlow<Login?>(null)

    val userLogin: StateFlow<Login?> get() = _login
    fun setUserData(login: Login) = runBlocking {
        _login.emit(login) }
}