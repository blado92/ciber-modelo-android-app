package com.cibermodelo.data.di

import com.cibermodelo.data.apiservice.DeceasedApiService
import com.cibermodelo.data.apiservice.UserApiService
import com.cibermodelo.data.repository.DeceasedRepositoryImpl
import com.cibermodelo.data.repository.LoginRepositoryImpl
import com.cibermodelo.data.repository.UserRepositoryImpl
import com.cibermodelo.databasemanager.daos.UserDao
import com.cibermodelo.domain.repository.DeceasedRepository
import com.cibermodelo.domain.repository.LoginRepository
import com.cibermodelo.domain.repository.UserRepository
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

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ) : UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideDeceasedRepository(
        deceasedApiService: DeceasedApiService,
        dispatcher: CoroutineContext
    ) : DeceasedRepository {
        return DeceasedRepositoryImpl(deceasedApiService, dispatcher)
    }

}