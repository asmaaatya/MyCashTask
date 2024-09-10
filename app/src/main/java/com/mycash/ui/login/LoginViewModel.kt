package com.mycash.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.login.LoginResponse
import com.mycash.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val loginResult = MutableLiveData<ResultApiCall<LoginResponse>>()
        fun login(email: String, password: String) {
            viewModelScope.launch {
                loginResult.value = ResultApiCall.Loading
                val result = loginUseCase.login(email, password)
                loginResult.value = result
            }
        }
    }

