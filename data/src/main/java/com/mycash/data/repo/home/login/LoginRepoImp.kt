package com.mycash.data.repo.home.login

import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.LoginResponse
import com.mycash.domain.models.requests.LogInRequest
import com.mycash.domain.repo.LoginRepository
import javax.inject.Inject

class LoginRepoImp @Inject constructor(private val apiService: ApiService) : LoginRepository {
    override suspend fun login(logInRequest: LogInRequest): ResultApiCall<LoginResponse> {
        return try {
            val response = apiService.login(logInRequest)
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