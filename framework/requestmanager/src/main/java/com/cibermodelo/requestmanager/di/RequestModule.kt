package com.cibermodelo.requestmanager.di

import com.cibermodelo.data.apiservice.DeceasedApiService
import com.cibermodelo.data.apiservice.FieldApiService
import com.cibermodelo.data.apiservice.UserApiService
import com.cibermodelo.requestmanager.ApiService
import com.cibermodelo.requestmanager.apiservice.DeceasedApiServiceImpl
import com.cibermodelo.requestmanager.apiservice.FieldApiServiceImpl
import com.cibermodelo.requestmanager.apiservice.UserApiServiceImpl
import com.cibermodelo.requestmanager.manager.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestModule {

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
    fun provideDeceasedApiService(
        service: ApiService
    ): DeceasedApiService {
        return DeceasedApiServiceImpl(service)
    }

    @Provides
    @Singleton
    fun provideFieldApiService(
        service: ApiService
    ): FieldApiService {
        return FieldApiServiceImpl(service)
    }

}