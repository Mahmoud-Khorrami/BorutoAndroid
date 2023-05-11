package com.mahapp1397.borutoapp.domain.repository

import androidx.paging.PagingData
import com.mahapp1397.borutoapp.domain.models.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource
{
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
}