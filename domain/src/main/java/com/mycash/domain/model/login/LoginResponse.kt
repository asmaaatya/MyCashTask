package com.mycash.domain.model.login

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val response_code: Int,
    val success: Boolean
)