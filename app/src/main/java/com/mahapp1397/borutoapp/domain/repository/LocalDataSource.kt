package com.mahapp1397.borutoapp.domain.repository

import com.mahapp1397.borutoapp.domain.models.Hero

interface LocalDataSource
{
    suspend fun getSelectedHero(heroId: Int): Hero
}