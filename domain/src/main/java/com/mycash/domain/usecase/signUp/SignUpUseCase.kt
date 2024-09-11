package com.mycash.domain.usecase.signUp

import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.ValidationState
import com.mycash.domain.models.requests.SingUpRequest
import com.mycash.domain.models.responses.SignUpResponse
import com.mycash.domain.repo.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository,
    private val validation: ValidationSignUpUseCase
) {

    suspend fun signUp(request: SingUpRequest): ResultApiCall<SignUpResponse> {

        return when (val valid = validation(request)) {
            is ResultApiCall.InputState -> {
                if (valid.errorState == ValidationState.Valid)
                    signUpRepository.signUp(request)
                else valid
            }
             else -> valid
        }
    }
}