package com.mycash.domain.models.beans.login

data class Login(
    val addresses: List<Addresse>,
    val balance: String,
    val email: String,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val status: Int,
    val token: String,
    val type: Int
)