package com.cibermodelo.domain.usecase

import com.cibermodelo.base.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserInformationUseCase {
    suspend operator fun invoke() : Flow<User>
}