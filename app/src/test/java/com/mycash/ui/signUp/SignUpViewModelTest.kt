package com.mycash.ui.signUp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mycash.TestDummyData.user
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.ValidationState
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
    fun signUp_validInput_success() = runTest {
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

    @Test
    fun signUp_emptyName_failure() = runTest {
        val useCase: SignUpUseCase = mockk()
        val viewModel = SignUpViewModel(useCase)

        coEvery { useCase.signUp(user.copy(name = "")) } returns ResultApiCall.InputState(
            ValidationState.EmptyName
        )

        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
        viewModel.signupResult.observeForever(observer)

        viewModel.signUp(user.copy(name = ""))
        advanceUntilIdle()

        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.EmptyName)) }

        viewModel.signupResult.removeObserver(observer)
    }


    @Test
    fun signUp_emptyEmail_failure() = runTest {
        val useCase: SignUpUseCase = mockk()
        val viewModel = SignUpViewModel(useCase)

        coEvery { useCase.signUp(user.copy(email = "")) } returns ResultApiCall.InputState(
            ValidationState.EmptyEmail
        )

        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
        viewModel.signupResult.observeForever(observer)

        viewModel.signUp(user.copy(email = ""))
        advanceUntilIdle()

        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.EmptyEmail)) }

        viewModel.signupResult.removeObserver(observer)
    }

    @Test
    fun signUp_emptyPassword_failure() = runTest {
        val useCase: SignUpUseCase = mockk()
        val viewModel = SignUpViewModel(useCase)

        coEvery { useCase.signUp(user.copy(password = "")) } returns ResultApiCall.InputState(
            ValidationState.EmptyPassword
        )

        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
        viewModel.signupResult.observeForever(observer)

        viewModel.signUp(user.copy(password = ""))
        advanceUntilIdle()

        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.EmptyPassword)) }

        viewModel.signupResult.removeObserver(observer)
    }

    @Test
    fun signUp_emptyPhone_failure() = runTest {
        val useCase: SignUpUseCase = mockk()
        val viewModel = SignUpViewModel(useCase)

        coEvery { useCase.signUp(user.copy(phone = "")) } returns ResultApiCall.InputState(
            ValidationState.EmptyPhone
        )

        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
        viewModel.signupResult.observeForever(observer)

        viewModel.signUp(user.copy(phone = ""))
        advanceUntilIdle()

        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.EmptyPhone)) }

        viewModel.signupResult.removeObserver(observer)
    }

    @Test
    fun signUp_invalidEmailFormat_failure() = runTest {
        val useCase: SignUpUseCase = mockk()
        val viewModel = SignUpViewModel(useCase)

        coEvery { useCase.signUp(user.copy(email = "invalid-email")) } returns ResultApiCall.InputState(
            ValidationState.IncorrectEmail
        )

        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
        viewModel.signupResult.observeForever(observer)

        viewModel.signUp(user.copy(email = "invalid-email"))
        advanceUntilIdle()

        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.IncorrectEmail)) }

        viewModel.signupResult.removeObserver(observer)
    }

    @Test
    fun signUp_invalidPhoneNumberFormat_failure() = runTest {
        val useCase: SignUpUseCase = mockk()
        val viewModel = SignUpViewModel(useCase)

        coEvery { useCase.signUp(user.copy(phone = "0123456789012345")) } returns ResultApiCall.InputState(
            ValidationState.MustHasMore11LengthPhone
        )

        val observer = mockk<Observer<ResultApiCall<SignUpResponse>>>(relaxed = true)
        viewModel.signupResult.observeForever(observer)

        viewModel.signUp(user.copy(phone = "0123456789012345"))
        advanceUntilIdle()

        verify { observer.onChanged(ResultApiCall.Loading) }
        verify { observer.onChanged(ResultApiCall.InputState(ValidationState.MustHasMore11LengthPhone)) }

        viewModel.signupResult.removeObserver(observer)
    }


}