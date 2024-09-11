package com.mycash.ui.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.requests.SingUpRequest
import com.mycash.domain.models.responses.SignUpResponse
import com.mycash.domain.usecase.signUp.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    val signupResult = MutableLiveData<ResultApiCall<SignUpResponse>>()

    fun signUp(request: SingUpRequest) {
        viewModelScope.launch {
            signupResult.value = ResultApiCall.Loading
            val result = signUpUseCase.signUp(request)
            signupResult.value = result
        }
    }
}