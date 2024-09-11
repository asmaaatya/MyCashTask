package com.mycash.domain.models

sealed class ResultApiCall<out T> {
    data class Success<out T>(val data: T) : ResultApiCall<T>()
    data class Failure(val message: String) : ResultApiCall<Nothing>()
    data class InputState(val errorState: ValidationState) : ResultApiCall<Nothing>()
    data object Loading : ResultApiCall<Nothing>()
}