package com.mycash.domain.usecase

import com.mycash.domain.model.ResultApiCall
import com.mycash.domain.model.login.LoginResponse
import com.mycash.domain.repo.LoginRepository
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
        val loginUseCase = LoginUseCase(loginRepository)
        val name = "asmmaa"
        val password = "12345678"
        val loginResponse = mockk<LoginResponse>()
        //when
        coEvery {
            loginUseCase.login(
                name,
                password
            )
        } coAnswers { ResultApiCall.Success(loginResponse) }
        //then
        val result = loginUseCase.login(
            name,
            password
        )
        assertEquals(ResultApiCall.Success(loginResponse), result)
    }
}