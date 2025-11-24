package com.cibermodelo.cibermodeloapplication.di

import com.cibermodelo.cibermodeloapplication.BuildConfig
import com.cibermodelo.domain.repository.DeceasedRepository
import com.cibermodelo.domain.repository.FieldRepository
import com.cibermodelo.domain.repository.LoginRepository
import com.cibermodelo.domain.repository.UserRepository
import com.cibermodelo.domain.usecase.GetDeceasedByUserUseCase
import com.cibermodelo.domain.usecase.GetDeceasedByUserUseCaseImpl
import com.cibermodelo.domain.usecase.GetFieldsByQueryTypeAndAccessLevelUseCase
import com.cibermodelo.domain.usecase.GetFieldsByQueryTypeAndAccessLevelUseCaseImpl
import com.cibermodelo.domain.usecase.GetQueryTypesByUserAndDeceasedUseCase
import com.cibermodelo.domain.usecase.GetQueryTypesByUserAndDeceasedUseCaseImpl
import com.cibermodelo.domain.usecase.IsUserLoggedInUseCase
import com.cibermodelo.domain.usecase.IsUserLoggedInUseCaseImpl
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
        userRepository: UserRepository,
        dispatcher: CoroutineContext
    ): LoginUseCase = LoginUseCaseImpl(loginRepository, userRepository, dispatcher)

    @Provides
    @Singleton
    fun provideIsUserLoggedInUseCase(
        userRepository: UserRepository,
        dispatcher: CoroutineContext
    ): IsUserLoggedInUseCase = IsUserLoggedInUseCaseImpl(userRepository, dispatcher)

    @Provides
    @Singleton
    fun provideGetDeceasedByUserUseCase(
        deceasedRepository: DeceasedRepository,
        userRepository: UserRepository,
        dispatcher: CoroutineContext
    ): GetDeceasedByUserUseCase =
        GetDeceasedByUserUseCaseImpl(deceasedRepository, userRepository, dispatcher)

    @Provides
    @Singleton
    fun provideGetQueryTypesByUserAndDeceasedUseCase(
        deceasedRepository: DeceasedRepository,
        userRepository: UserRepository,
        dispatcher: CoroutineContext
    ): GetQueryTypesByUserAndDeceasedUseCase =
        GetQueryTypesByUserAndDeceasedUseCaseImpl(deceasedRepository, userRepository, dispatcher)

    @Provides
    @Singleton
    fun provideGetFieldsByQueryTypeAndAccessLevelUseCase(
        fieldRepository: FieldRepository,
        dispatcher: CoroutineContext
    ): GetFieldsByQueryTypeAndAccessLevelUseCase =
        GetFieldsByQueryTypeAndAccessLevelUseCaseImpl(
            fieldRepository,
            dispatcher
        )

}