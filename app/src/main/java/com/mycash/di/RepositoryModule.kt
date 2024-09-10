package com.mycash.di

import com.mycash.data.repo.home.HomeRepoImp
import com.mycash.data.repo.home.login.LoginRepoImp
import com.mycash.data.repo.home.signUp.SignUpRepoImp
import com.mycash.domain.repo.HomeRepository
import com.mycash.domain.repo.LoginRepository
import com.mycash.domain.repo.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(
        homeRepoImp: HomeRepoImp
    ): HomeRepository {
        return homeRepoImp
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginRepoImp: LoginRepoImp
    ): LoginRepository {
        return loginRepoImp
    }

    @Provides
    @Singleton
    fun provideSignUpRepository(
        signUpRepoImp: SignUpRepoImp
    ): SignUpRepository {
        return signUpRepoImp
    }
}