package com.mycash.domain.models.requests

data class SingUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val confirmPassword: String = ""
)