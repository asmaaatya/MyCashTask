package com.mycash.data.repo.home.signUp

import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.signUp.SignUpResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class SignUpRepoImpTest {

    @Test
    fun signUp() = runTest {

        val apiService: ApiService = mockk()
        val signUpRepoImp = SignUpRepoImp(apiService)

        val signUpResponse = mockk<SignUpResponse>(){
            every { success } returns true
            every { message } returns "Sign-up successful"
        }
        val name = "asmmaa"
        val email = "asmaa@gmail.com"
        val password = "12345678"
        val phone = "012134567896556"

        coEvery { apiService.signUp(name, email, password, phone) } coAnswers { signUpResponse}

        val result = signUpRepoImp.signUp(name, email, password, phone)
        assertEquals(ResultApiCall.Success(signUpResponse), result)
    }
}