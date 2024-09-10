package com.mycash.data.repo.home.login

import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.login.LoginResponse
import com.mycash.domain.repo.LoginRepository
import javax.inject.Inject

class LoginRepoImp @Inject constructor(private val apiService: ApiService) : LoginRepository {
    override suspend fun login(email: String, password: String): ResultApiCall<LoginResponse> {
        return try {
            val response = apiService.login(email, password)
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