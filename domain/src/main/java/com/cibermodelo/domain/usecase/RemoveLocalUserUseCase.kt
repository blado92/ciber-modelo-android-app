package com.cibermodelo.domain.usecase

import kotlinx.coroutines.flow.Flow

interface RemoveLocalUserUseCase {
    suspend operator fun invoke() : Flow<Boolean>
}