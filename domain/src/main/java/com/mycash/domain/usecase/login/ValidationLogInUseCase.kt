package com.mycash.domain.usecase.login
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.ValidationState
import com.mycash.domain.models.requests.LogInRequest
import javax.inject.Inject

class ValidationLogInUseCase @Inject constructor() {

    operator fun invoke(request: LogInRequest): ResultApiCall<com.mycash.domain.models.responses.LoginResponse> {
        return when {
            request.email.isEmpty() -> ResultApiCall.InputState(ValidationState.EmptyEmail)

            !android.util.Patterns.EMAIL_ADDRESS.matcher(request.email)
                .matches() -> ResultApiCall.InputState(
                ValidationState.IncorrectEmail
            )

            request.password.isEmpty() -> ResultApiCall.InputState(ValidationState.EmptyPassword)

            else -> ResultApiCall.InputState(ValidationState.Valid)
        }
    }
}