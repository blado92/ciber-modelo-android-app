package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.Resource
import com.cibermodelo.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend operator fun invoke(email: String, password: String) : Flow<Resource<User>>
}