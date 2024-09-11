package com.mycash.ui.signUp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mycash.TestDummyData.user
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.SignUpResponse
import com.mycash.domain.usecase.signUp.SignUpUseCase
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
class SignUpViewModelTest {
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
    fun signUp_validInput_success() = runTest{
        val useCase: SignUpUseCase = mockk()
        val viewModel = SignUpViewModel(useCase)
        val signUpResponse = mockk<SignUpResponse>()
        coEvery { useCase.signUp(user) } returns ResultApiCall.Success(signUpResponse)
        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
        viewModel.signupResult.observeForever(observer)
        viewModel.signUp(user)
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Loading) }
        advanceUntilIdle()
        verify { observer.onChanged(ResultApiCall.Success(signUpResponse)) }
        viewModel.signupResult.removeObserver(observer)
    }

//    @Test
//    fun signUp_emptyName_failure()= runTest{
//        val useCase: SignUpUseCase = mockk()
//        val viewModel = SignUpViewModel(useCase)
//        val name = ""
//        val email = "asmmaa@gmail.com"
//        val password = "12345678"
//        val phone = "01213456789"
//        coEvery { useCase.signUp(name,email, password,phone) } returns ResultApiCall.Failure("user name is required")
//        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
//        viewModel.signupResult.observeForever(observer)
//        viewModel.signUp(name, email, password, phone)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("user name is required")) }
//        viewModel.signupResult.removeObserver(observer)
//    }


//    @Test
//    fun signUp_emptyEmail_failure()= runTest{
//        val useCase: SignUpUseCase = mockk()
//        val viewModel = SignUpViewModel(useCase)
//        val name = "asmmaa"
//        val email = ""
//        val password = "12345678"
//        val phone = "01213456789"
//        coEvery { useCase.signUp(name,email, password,phone) } returns ResultApiCall.Failure("email is required")
//        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
//        viewModel.signupResult.observeForever(observer)
//        viewModel.signUp(name, email, password, phone)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("email is required")) }
//        viewModel.signupResult.removeObserver(observer)
//    }
//
//    @Test
//    fun signUp_emptyPassword_failure() = runTest{
//        val useCase: SignUpUseCase = mockk()
//        val viewModel = SignUpViewModel(useCase)
//        val name = "asmmaa"
//        val email = "asmaa@gmail.com"
//        val password = ""
//        val phone = "01213456789"
//        coEvery { useCase.signUp(name,email, password,phone) } returns ResultApiCall.Failure("password is required")
//        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
//        viewModel.signupResult.observeForever(observer)
//        viewModel.signUp(name, email, password, phone)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("password is required")) }
//        viewModel.signupResult.removeObserver(observer)
//    }
//
//    @Test
//    fun signUp_emptyPhone_failure() = runTest {
//        val useCase: SignUpUseCase = mockk()
//        val viewModel = SignUpViewModel(useCase)
//        val name = "asmmaa"
//        val email = "asmaa@gmail.com"
//        val password = "12345678"
//        val phone = ""
//        coEvery { useCase.signUp(name,email, password,phone) } returns ResultApiCall.Failure("phone is required")
//        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
//        viewModel.signupResult.observeForever(observer)
//        viewModel.signUp(name, email, password, phone)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("phone is required")) }
//        viewModel.signupResult.removeObserver(observer)
//    }
//
//    @Test
//    fun signUp_invalidEmailFormat_failure() = runTest{
//        val useCase: SignUpUseCase = mockk()
//        val viewModel = SignUpViewModel(useCase)
//        val name = "asmmaa"
//        val email = "asmaa@gmailcom"
//        val password = "12345678"
//        val phone = "01213456789"
//        coEvery { useCase.signUp(name,email, password,phone) } returns ResultApiCall.Failure("enter valid email format")
//        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
//        viewModel.signupResult.observeForever(observer)
//        viewModel.signUp(name, email, password, phone)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("enter valid email format")) }
//        viewModel.signupResult.removeObserver(observer)
//    }
//
//    @Test
//    fun signUp_invalidPhoneNumberFormat_failure() = runTest {
//        val useCase: SignUpUseCase = mockk()
//        val viewModel = SignUpViewModel(useCase)
//        val name = "asmmaa"
//        val email = "asmaa@gmail.com"
//        val password = "12345678"
//        val phone = "012134567896556"
//        coEvery { useCase.signUp(name,email, password,phone) } returns ResultApiCall.Failure("enter valid phone number")
//        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
//        viewModel.signupResult.observeForever(observer)
//        viewModel.signUp(name, email, password, phone)
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Loading) }
//        advanceUntilIdle()
//        verify { observer.onChanged(ResultApiCall.Failure("enter valid phone number")) }
//        viewModel.signupResult.removeObserver(observer)
//    }



}