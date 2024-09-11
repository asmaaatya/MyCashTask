package com.mycash.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mycash.TestDummyData.loggedInUser
import com.mycash.domain.models.ResultApiCall
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

//
//    @Test
//    fun login_EmptyEmailTest() = runTest {
//        val useCase: LoginUseCase = mockk()
//        val viewModel = LoginViewModel(useCase)
//        val email = ""
//        val password = "12345678"
//        coEvery {
//            useCase.login(
//                email,
//                password
//            )
//        } returns ResultApiCall.Failure("email is required")
//        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
//        viewModel.loginResult.observeForever(observer)
//        viewModel.login(email, password)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("email is required")) }
//        viewModel.loginResult.removeObserver(observer)
//    }
//
//    @Test
//    fun login_EmptyPasswordTest() = runTest {
//        val useCase: LoginUseCase = mockk()
//        val viewModel = LoginViewModel(useCase)
//        val email = "asmmaa@gmail.com"
//        val password = ""
//        coEvery {
//            useCase.login(
//                email,
//                password
//            )
//        } returns ResultApiCall.Failure("password is required")
//        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
//        viewModel.loginResult.observeForever(observer)
//        viewModel.login(email, password)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("password is required")) }
//        viewModel.loginResult.removeObserver(observer)
//    }
//
//    @Test
//    fun login_InvalidEmailFormatTest() = runTest {
//        val useCase: LoginUseCase = mockk()
//        val viewModel = LoginViewModel(useCase)
//        val email = "asmaa-mail"
//        val password = "12345678"
//        coEvery {
//            useCase.login(
//                email,
//                password
//            )
//        } returns ResultApiCall.Failure("invalid email format")
//        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
//        viewModel.loginResult.observeForever(observer)
//        viewModel.login(email, password)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("invalid email format")) }
//        viewModel.loginResult.removeObserver(observer)
//    }
//
//    @Test
//    fun login_IncorrectPasswordTest() = runTest {
//        val useCase: LoginUseCase = mockk()
//        val viewModel = LoginViewModel(useCase)
//        val email = "asmaa@gmail.com"
//        val password = "13456789"
//        coEvery {
//            useCase.login(
//                email,
//                password
//            )
//        } returns ResultApiCall.Failure("incorrect password")
//        val observer = mockk<Observer<ResultApiCall<LoginResponse>>>(relaxed = true)
//        viewModel.loginResult.observeForever(observer)
//        viewModel.login(email, password)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("incorrect password")) }
//        viewModel.loginResult.removeObserver(observer)
//    }

}