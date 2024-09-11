package com.mycash.domain.usecase.signUp

import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.ValidationState
import com.mycash.domain.models.requests.SingUpRequest
import com.mycash.domain.models.responses.SignUpResponse
import javax.inject.Inject

class ValidationSignUpUseCase @Inject constructor() {

    operator fun invoke(request: SingUpRequest): ResultApiCall<SignUpResponse> {
        return when {
            request.name.isEmpty() -> ResultApiCall.InputState(ValidationState.EmptyName)

            request.name.length < 14 -> ResultApiCall.InputState(ValidationState.MustHasMore14LengthName)

            request.email.isEmpty() -> ResultApiCall.InputState(ValidationState.EmptyEmail)

            !android.util.Patterns.EMAIL_ADDRESS.matcher(request.email).matches() -> ResultApiCall.InputState(ValidationState.IncorrectEmail)

            request.password.isEmpty() -> ResultApiCall.InputState(ValidationState.EmptyPassword)

            request.password.length < 8 -> ResultApiCall.InputState(ValidationState.MustHasMore8LengthPassword)

            request.confirmPassword.isEmpty() -> ResultApiCall.InputState(ValidationState.EmptyConfirmPassword)

            request.password != request.confirmPassword -> ResultApiCall.InputState(ValidationState.PasswordNotMatch)

            request.phone.isEmpty() -> ResultApiCall.InputState(ValidationState.EmptyPhone)

            request.phone.length < 11 -> ResultApiCall.InputState(ValidationState.MustHasMore11LengthPhone)

            else -> ResultApiCall.InputState(ValidationState.Valid)
        }
    }

}
