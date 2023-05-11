package com.mahapp1397.borutoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations
{
    suspend fun saveOnBoardingState(complete: Boolean)

    fun readOnBoardingState(): Flow<Boolean>
}