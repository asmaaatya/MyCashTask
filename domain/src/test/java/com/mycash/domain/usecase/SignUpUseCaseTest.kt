package com.mycash.domain.usecase

import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.signUp.SignUpResponse
import com.mycash.domain.repo.SignUpRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class SignUpUseCaseTest {

    @Test
    fun signUp() = runTest {
        //given
        val signUpRepository: SignUpRepository = mockk()
        val signUpUseCase = SignUpUseCase(signUpRepository)
        val name = "asmmaa"
        val email = "asmaa@gmail.com"
        val password = "12345678"
        val phone = "012134567896556"
        val signUpResponse = mockk<SignUpResponse>()
        //when
        coEvery {
            signUpUseCase.signUp(
                name,
                email,
                password,
                phone
            )
        } coAnswers { ResultApiCall.Success(signUpResponse) }
        //then
        val result = signUpUseCase.signUp(name, email, password, phone)
        assertEquals(ResultApiCall.Success(signUpResponse), result)
    }
}