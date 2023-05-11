package com.mahapp1397.borutoapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.mahapp1397.borutoapp.data.repository.Repository
import com.mahapp1397.borutoapp.domain.models.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(private val repository: Repository)
{
    operator fun invoke(query: String): Flow<PagingData<Hero>>
    {
        return repository.searchHeroes(query)
    }
}