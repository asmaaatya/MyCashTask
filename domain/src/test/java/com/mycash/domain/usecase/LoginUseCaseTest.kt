package com.mycash.domain.usecase

import com.mycash.domain.TestDummyData.loggedInUser
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.LoginResponse
import com.mycash.domain.repo.LoginRepository
import com.mycash.domain.usecase.login.LoginUseCase
import com.mycash.domain.usecase.login.ValidationLogInUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginUseCaseTest {

    @Test
    fun login() = runTest {
        //given
        val loginRepository: LoginRepository = mockk()
        val validationUseCase= mockk<ValidationLogInUseCase>()
        val loginUseCase = LoginUseCase(loginRepository,validationUseCase)
        val loginResponse = mockk<LoginResponse>()
        //when
        coEvery {
            loginUseCase.login(
              loggedInUser
            )
        } coAnswers { ResultApiCall.Success(loginResponse) }
        //then
        val result = loginUseCase.login(
            loggedInUser
        )
        assertEquals(ResultApiCall.Success(loginResponse), result)
    }
}