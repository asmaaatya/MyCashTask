package com.mycash.di

import com.mycash.domain.repo.HomeRepository
import com.mycash.domain.repo.LoginRepository
import com.mycash.domain.repo.SignUpRepository
import com.mycash.domain.usecase.HomeUseCase
import com.mycash.domain.usecase.login.LoginUseCase
import com.mycash.domain.usecase.login.ValidationLogInUseCase
import com.mycash.domain.usecase.signUp.SignUpUseCase
import com.mycash.domain.usecase.signUp.ValidationSignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideHomeUseCase(
        homeRepository: HomeRepository,
    ): HomeUseCase {
        return HomeUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
        validation: ValidationLogInUseCase,
    ): LoginUseCase {
        return LoginUseCase(loginRepository,validation)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        signUpRepository: SignUpRepository,
        validation: ValidationSignUpUseCase,
    ): SignUpUseCase {
        return SignUpUseCase(signUpRepository, validation)
    }
}