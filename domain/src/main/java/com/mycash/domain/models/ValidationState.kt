package com.mycash.domain.models

sealed class ValidationState {
    data object EmptyName : ValidationState()
    data object MustHasMore14LengthName : ValidationState()
    data object EmptyEmail : ValidationState()
    data object IncorrectEmail : ValidationState()
    data object EmptyPassword : ValidationState()
    data object MustHasMore8LengthPassword : ValidationState()
    data object EmptyConfirmPassword : ValidationState()
    data object PasswordNotMatch : ValidationState()
    data object EmptyPhone : ValidationState()
    data object MustHasMore11LengthPhone : ValidationState()
    data object Valid : ValidationState()
}
