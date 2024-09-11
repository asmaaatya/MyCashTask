package com.mycash.data.repo.home.signUp

import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.requests.SingUpRequest
import com.mycash.domain.models.responses.SignUpResponse
import com.mycash.domain.repo.SignUpRepository
import javax.inject.Inject

class SignUpRepoImp @Inject constructor(private val apiService: ApiService) : SignUpRepository {

    override suspend fun signUp(request: SingUpRequest): ResultApiCall<SignUpResponse> {

        return try {
            val response = apiService.signUp(request)

            if (response.success) ResultApiCall.Success(response)
            else ResultApiCall.Failure("Error: ${response.message}")

        } catch (e: Exception) {
            ResultApiCall.Failure("Exception: ${e.message}")
        }
    }
}