package com.mahapp1397.borutoapp.domain.use_cases.get_selected_hero

import com.mahapp1397.borutoapp.data.repository.Repository
import com.mahapp1397.borutoapp.domain.models.Hero

class GetSelectedHeroUseCase(private val repository: Repository)
{
    suspend operator fun invoke(heroId: Int): Hero
    {
        return repository.getSelectedHero(heroId)
    }
}