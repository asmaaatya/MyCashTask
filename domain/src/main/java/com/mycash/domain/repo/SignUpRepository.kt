package com.mycash.domain.repo

import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.requests.SingUpRequest
import com.mycash.domain.models.responses.SignUpResponse

interface SignUpRepository {
    suspend fun signUp(request: SingUpRequest): ResultApiCall<SignUpResponse>

}