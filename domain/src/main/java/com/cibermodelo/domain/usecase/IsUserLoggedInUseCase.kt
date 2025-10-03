package com.cibermodelo.domain.usecase

import kotlinx.coroutines.flow.Flow

interface IsUserLoggedInUseCase {
    suspend operator fun invoke() : Flow<Boolean>
}