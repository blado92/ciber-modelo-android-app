package com.cibermodelo.cibermodeloapplication.di

import com.cibermodelo.cibermodeloapplication.BuildConfig
import com.cibermodelo.domain.repository.LoginRepository
import com.cibermodelo.domain.usecase.LoginUseCase
import com.cibermodelo.domain.usecase.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("baseBackendUrl")
    fun baseBackendUrlProvider(): String = BuildConfig.BASE_BE_URL

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO

    @Provides
    @Singleton
    fun provideLoginUseCase(
        loginRepository: LoginRepository,
        dispatcher: CoroutineContext
    ): LoginUseCase = LoginUseCaseImpl(loginRepository, dispatcher)

}