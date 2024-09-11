package com.mycash.domain.models.beans.signUp

data class SignUp(
    val id: Int,
    val addresses: List<Any>, // todo refactor this to address model
    val balance: Any, // todo refactor this to balance model
    val email: String,
    val image: String,
    val name: String,
    val phone: String,
    val status: Int,
    val token: String,
    val type: Int,
)