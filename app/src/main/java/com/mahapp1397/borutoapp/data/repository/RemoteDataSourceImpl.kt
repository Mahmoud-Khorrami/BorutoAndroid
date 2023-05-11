package com.mahapp1397.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mahapp1397.borutoapp.data.BorutoDatabase
import com.mahapp1397.borutoapp.data.paging_source.HeroRemoteMediator
import com.mahapp1397.borutoapp.data.paging_source.SearchHeroesSource
import com.mahapp1397.borutoapp.data.remote.BorutoApi
import com.mahapp1397.borutoapp.domain.models.Hero
import com.mahapp1397.borutoapp.domain.repository.RemoteDataSource
import com.mahapp1397.borutoapp.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase): RemoteDataSource
{
    private val heroDao = borutoDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>>
    {
        val pagingSourceFactory = {heroDao.getAllHero()}
         return Pager(
             config = PagingConfig(pageSize = ITEMS_PER_PAGE),
             remoteMediator = HeroRemoteMediator(borutoApi = borutoApi, borutoDatabase = borutoDatabase),
             pagingSourceFactory =  pagingSourceFactory).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>>
    {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {SearchHeroesSource(borutoApi = borutoApi, query = query)}).flow
    }

}