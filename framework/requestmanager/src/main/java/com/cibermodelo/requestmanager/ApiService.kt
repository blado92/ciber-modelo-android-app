package com.cibermodelo.requestmanager

import com.cibermodelo.requestmanager.model.DeceasedResponse
import com.cibermodelo.requestmanager.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("login")
    suspend fun login(
        @Query("email") apiKey: String,
        @Query("password") password: String
    ) : Response<UserResponse>

    @GET("deceased")
    suspend fun deceasedByUser(
        @Query("userId") userId: Int
    ) : Response<List<DeceasedResponse>>
}