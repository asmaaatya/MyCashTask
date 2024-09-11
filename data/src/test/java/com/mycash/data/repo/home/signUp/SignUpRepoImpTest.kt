package com.mycash.data.repo.home.signUp

import com.mycash.data.TestDummyData.user
import com.mycash.data.remote.apis.ApiService
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.SignUpResponse
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

        val signUpResponse = mockk<SignUpResponse>() {
            every { success } returns true
            every { message } returns "Sign-up successful"
        }

        coEvery { apiService.signUp(user) } coAnswers { signUpResponse }

        val result = signUpRepoImp.signUp(user)
        assertEquals(ResultApiCall.Success(signUpResponse), result)
    }
}