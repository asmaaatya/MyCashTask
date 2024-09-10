package com.mycash.domain.repo

import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.signUp.SignUpResponse

interface SignUpRepository {
    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        phone: String):ResultApiCall<SignUpResponse>
}