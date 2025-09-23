package com.cibermodelo.data.di

import com.cibermodelo.data.apiservice.UserApiService
import com.cibermodelo.data.apiservice.UserApiServiceImpl
import com.cibermodelo.data.repository.LoginRepositoryImpl
import com.cibermodelo.domain.repository.LoginRepository
import com.cibermodelo.requestmanager.ApiService
import com.cibermodelo.requestmanager.manager.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(
        @Named("baseBackendUrl") baseBackendUrl: String
    ): ApiService {
        return ServiceGenerator(baseBackendUrl).createService()
    }

    @Provides
    @Singleton
    fun provideUserApiService(
        service: ApiService
    ): UserApiService {
        return UserApiServiceImpl(service)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        userApiService: UserApiService,
        dispatcher: CoroutineContext
    ) : LoginRepository {
        return LoginRepositoryImpl(userApiService, dispatcher)
    }

}