package com.mycash.domain.repo

import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.LoginResponse
import com.mycash.domain.models.requests.LogInRequest

interface LoginRepository {
    suspend fun login(logInRequest: LogInRequest): ResultApiCall<LoginResponse>
}