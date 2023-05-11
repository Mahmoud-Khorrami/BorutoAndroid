package com.mahapp1397.borutoapp.data.repository

import com.mahapp1397.borutoapp.data.BorutoDatabase
import com.mahapp1397.borutoapp.domain.models.Hero
import com.mahapp1397.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase): LocalDataSource
{
    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero
    {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}