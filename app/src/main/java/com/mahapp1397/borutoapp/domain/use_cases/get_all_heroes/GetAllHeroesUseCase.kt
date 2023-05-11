package com.mahapp1397.borutoapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.mahapp1397.borutoapp.data.repository.Repository
import com.mahapp1397.borutoapp.domain.models.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(private val repository: Repository)
{
    operator fun invoke(): Flow<PagingData<Hero>>
    {
        return repository.getAllHeroes()
    }
}