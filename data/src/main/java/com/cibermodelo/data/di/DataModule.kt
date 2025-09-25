package com.cibermodelo.data.di

import com.cibermodelo.data.apiservice.UserApiService
import com.cibermodelo.data.repository.LoginRepositoryImpl
import com.cibermodelo.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        userApiService: UserApiService,
        dispatcher: CoroutineContext
    ) : LoginRepository {
        return LoginRepositoryImpl(userApiService, dispatcher)
    }

}