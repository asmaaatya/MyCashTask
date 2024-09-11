package com.mycash.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.LoginResponse
import com.mycash.domain.models.requests.LogInRequest
import com.mycash.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val loginResult = MutableLiveData<ResultApiCall<LoginResponse>>()
        fun login(logInRequest: LogInRequest) {
            viewModelScope.launch {
                loginResult.value = ResultApiCall.Loading
                val result = loginUseCase.login(logInRequest)
                loginResult.value = result
            }
        }
    }

