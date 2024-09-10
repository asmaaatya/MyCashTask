package com.mycash.ui.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.signUp.SignUpResponse
import com.mycash.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    val signupResult = MutableLiveData<ResultApiCall<SignUpResponse>>()
    fun signUp(name: String,
               email: String,
               password: String,
               phone: String) {
        viewModelScope.launch {
            signupResult.value = ResultApiCall.Loading
            val result = signUpUseCase.signUp(name, email, password, phone)
            signupResult.value = result
        }
    }
}