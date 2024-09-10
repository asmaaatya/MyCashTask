package com.mycash.data.repo.home.signUp

import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.signUp.SignUpResponse
import com.mycash.domain.repo.HomeRepository
import com.mycash.domain.repo.SignUpRepository
import javax.inject.Inject

class SignUpRepoImp @Inject constructor(private val apiService: ApiService) : SignUpRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        phone: String
    ): ResultApiCall<SignUpResponse> {
        return try {
            val response = apiService.signUp(name,email, password,phone)
            if (response.success) {
                ResultApiCall.Success(response)
            } else {
                ResultApiCall.Failure("Error: ${response.message}")
            }
        } catch (e: Exception) {
            ResultApiCall.Failure("Exception: ${e.message}")
        }
    }
}