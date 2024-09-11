package com.mycash.domain.usecase

import com.mycash.domain.TestDummyData.user
import com.mycash.domain.models.ResultApiCall
import com.mycash.domain.models.responses.SignUpResponse
import com.mycash.domain.repo.SignUpRepository
import com.mycash.domain.usecase.signUp.SignUpUseCase
import com.mycash.domain.usecase.signUp.ValidationSignUpUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class SignUpUseCaseTest {

    @Test
    fun signUp() = runTest {

        //Given
        val signUpRepository: SignUpRepository = mockk()
        val validationUseCase= mockk<ValidationSignUpUseCase>()
        val signUpUseCase = SignUpUseCase(signUpRepository,validationUseCase)
        val signUpResponse = mockk<SignUpResponse>()

        //When
        coEvery {
            signUpUseCase.signUp(user)
        } coAnswers { ResultApiCall.Success(signUpResponse)
        }

        //Then
        val result = signUpUseCase.signUp(user)
        assertEquals(ResultApiCall.Success(signUpResponse), result)
    }
}