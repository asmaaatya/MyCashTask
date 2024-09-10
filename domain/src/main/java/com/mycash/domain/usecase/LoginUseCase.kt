package com.mycash.domain.usecase

import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.login.LoginResponse
import com.mycash.domain.repo.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun login(email: String, password: String): ResultApiCall<LoginResponse> {
        return loginRepository.login(email, password)
    }
}