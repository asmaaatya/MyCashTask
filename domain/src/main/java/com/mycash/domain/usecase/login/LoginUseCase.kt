package com.mycash.domain.usecase.login

import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.ValidationState
import com.mycash.domain.models.responses.LoginResponse
import com.mycash.domain.models.requests.LogInRequest
import com.mycash.domain.repo.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val validation: ValidationLogInUseCase
) {
    suspend fun login(logInRequest: LogInRequest): ResultApiCall<LoginResponse> {

        return when (val valid = validation(logInRequest)) {
            is ResultApiCall.InputState -> {
                if (valid.errorState == ValidationState.Valid)
                    loginRepository.login(logInRequest)
                else valid
            }

            else -> valid
        }
    }
}