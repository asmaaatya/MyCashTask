package com.mycash.domain.repo

import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.login.LoginResponse

interface LoginRepository {
    suspend fun login(email: String, password: String): ResultApiCall<LoginResponse>
}