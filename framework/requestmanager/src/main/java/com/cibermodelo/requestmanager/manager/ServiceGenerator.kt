package com.cibermodelo.requestmanager.manager

import com.cibermodelo.requestmanager.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceGenerator(
    private val baseUrl: String
) {

    private lateinit var retrofit: Retrofit

    init {
        setupRetrofit()
    }

    private fun setupRetrofit() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            //.addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}