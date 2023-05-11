package com.mahapp1397.borutoapp.data.repository

import androidx.paging.PagingData
import com.mahapp1397.borutoapp.domain.models.Hero
import com.mahapp1397.borutoapp.domain.repository.DataStoreOperations
import com.mahapp1397.borutoapp.domain.repository.LocalDataSource
import com.mahapp1397.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreOperations)
{

    fun getAllHeroes(): Flow<PagingData<Hero>>
    {
        return remoteDataSource.getAllHeroes()
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>>
    {
        return remoteDataSource.searchHeroes(query)
    }

    suspend fun getSelectedHero(heroId: Int): Hero
    {
        return localDataSource.getSelectedHero(heroId)
    }

    suspend fun saveOnBoardingState(complete: Boolean)
    {
        dataStore.saveOnBoardingState(complete)
    }

    fun readOnBoardingState(): Flow<Boolean>
    {
        return dataStore.readOnBoardingState()
    }
}

