package com.mycash.data.repo.home.login

import com.mycash.data.TestDummyData.loggedInUser
import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.LoginResponse
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
                every { message } returns "Log_In successful"
            }


            coEvery { apiService.login(loggedInUser) } coAnswers { loginResponse}

            val result = loginRepoImp.login(loggedInUser)
            assertEquals(ResultApiCall.Success(loginResponse), result)
        }
    }
