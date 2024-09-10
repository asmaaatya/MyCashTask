package com.mycash.data.repo.home.login

import com.mycash.data.remote.apis.ApiService
import com.mycash.data.repo.home.signUp.SignUpRepoImp
import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.login.LoginResponse
import com.mycash.domain.model.signUp.SignUpResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test
@ExperimentalCoroutinesApi
class LoginRepoImpTest {

    @Test
    fun login() = runTest {

            val apiService: ApiService = mockk()
            val loginRepoImp = LoginRepoImp(apiService)

            val loginResponse = mockk<LoginResponse>(){
                every { success } returns true
                every { message } returns "Sign-up successful"
            }

            val email = "asmaa@gmail.com"
            val password = "12345678"


            coEvery { apiService.login( email, password) } coAnswers { loginResponse}

            val result = loginRepoImp.login( email, password)
            assertEquals(ResultApiCall.Success(loginResponse), result)
        }
    }
