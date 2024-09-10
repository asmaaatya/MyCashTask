package com.mycash.di

import com.mycash.domain.repo.HomeRepository
import com.mycash.domain.repo.LoginRepository
import com.mycash.domain.repo.SignUpRepository
import com.mycash.domain.usecase.HomeUseCase
import com.mycash.domain.usecase.LoginUseCase
import com.mycash.domain.usecase.SignUpUseCase
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
        homeRepository: HomeRepository
    ): HomeUseCase {
        return HomeUseCase(homeRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        loginRepository: LoginRepository
    ): LoginUseCase {
        return LoginUseCase(loginRepository)
    }
    @Provides
    @Singleton
    fun provideSignUpUseCase(
       signUpRepository: SignUpRepository
    ): SignUpUseCase {
        return SignUpUseCase(signUpRepository)
    }
}