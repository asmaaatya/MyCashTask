package com.mycash.domain.usecase

import com.mycash.domain.repo.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val signUpRepository: SignUpRepository) {
    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        phone: String
    ) = signUpRepository.signUp(name, email, password, phone)
}