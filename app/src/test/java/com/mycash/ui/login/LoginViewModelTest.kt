package com.mycash.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mycash.TestDummyData.email
import com.mycash.TestDummyData.loggedInUser
import com.mycash.TestDummyData.password
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.ValidationState
import com.mycash.domain.models.requests.LogInRequest
import com.mycash.domain.models.responses.LoginResponse
import com.mycash.domain.usecase.login.LoginUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun login_ValidCredentialsTest() = runTest {
        val useCase: LoginUseCase = mockk()
        val viewModel = LoginViewModel(useCase)
        val loginResponse = mockk<LoginResponse>()
        coEvery { useCase.login(loggedInUser) } returns ResultApiCall.Success(loginResponse)
        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
        viewModel.loginResult.observeForever(observer)
        viewModel.login(loggedInUser)
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Loading) }
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Success(loginResponse)) }
        viewModel.loginResult.removeObserver(observer)
    }


    @Test
    fun login_EmptyEmailTest() = runTest {
        val useCase: LoginUseCase = mockk()
        val viewModel = LoginViewModel(useCase)
        val logInRequest = LogInRequest("", password)

        coEvery { useCase.login(logInRequest) } returns ResultApiCall.InputState(ValidationState.EmptyEmail)

        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
        viewModel.loginResult.observeForever(observer)


        viewModel.login(logInRequest)

        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.EmptyEmail)) }

        viewModel.loginResult.removeObserver(observer)

    }

    @Test
    fun login_EmptyPasswordTest() = runTest {

        val useCase: LoginUseCase = mockk()
        val viewModel = LoginViewModel(useCase)
        val logInRequest = LogInRequest(email, "")

        coEvery { useCase.login(logInRequest) } returns ResultApiCall.InputState(ValidationState.EmptyPassword)

        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
        viewModel.loginResult.observeForever(observer)


        viewModel.login(logInRequest)
        advanceUntilIdle()

        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.EmptyPassword)) }

        viewModel.loginResult.removeObserver(observer)
    }

    @Test
    fun login_InvalidEmailFormatTest() = runTest {
        val useCase: LoginUseCase = mockk()
        val viewModel = LoginViewModel(useCase)
        val logInRequest = LogInRequest("invalid-email", password)

        coEvery { useCase.login(logInRequest) } returns ResultApiCall.InputState(ValidationState.IncorrectEmail)

        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
        viewModel.loginResult.observeForever(observer)


        viewModel.login(logInRequest)
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.IncorrectEmail)) }

        viewModel.loginResult.removeObserver(observer)
    }

    @Test
    fun login_IncorrectPasswordTest() = runTest {

        val useCase: LoginUseCase = mockk()
        val viewModel = LoginViewModel(useCase)
        val logInRequest = LogInRequest(email, "wrongPassword")

        coEvery { useCase.login(logInRequest) } returns ResultApiCall.Failure("incorrect password")

        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
        viewModel.loginResult.observeForever(observer)

        viewModel.login(logInRequest)
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.Failure("incorrect password")) }

        viewModel.loginResult.removeObserver(observer)
    }

}