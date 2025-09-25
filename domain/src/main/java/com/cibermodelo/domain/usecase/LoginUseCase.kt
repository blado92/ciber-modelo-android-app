package com.cibermodelo.domain.usecase

import com.cibermodelo.base.common.ResourceApi
import com.cibermodelo.base.model.User
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend operator fun invoke(email: String, password: String) : Flow<ResourceApi<User>>
}