package com.mycash.domain.model.signUp

data class SignUpResponse(
    val `data`: Data,
    val message: String,
    val response_code: Int,
    val success: Boolean
)