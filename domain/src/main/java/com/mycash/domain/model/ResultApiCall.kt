package com.mycash.domain.model

sealed class ResultApiCall<out T> {
    data class Success<out s>(val data: s) : ResultApiCall<s>()
    data class Failure(val message: String) : ResultApiCall<Nothing>()
    data object Loading : ResultApiCall<Nothing>()
}