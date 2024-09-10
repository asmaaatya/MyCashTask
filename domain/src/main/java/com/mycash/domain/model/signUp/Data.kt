package com.mycash.domain.model.signUp

data class Data(
    val addresses: List<Any>,
    val balance: Any,
    val email: String,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val status: Int,
    val token: String,
    val type: Int
)