package com.mahapp1397.borutoapp.domain.use_cases.save_onboarding

import com.mahapp1397.borutoapp.data.repository.Repository

class SaveOnBoardingUseCase(private val repository: Repository)
{
    suspend operator fun invoke(complete: Boolean)
    {
        repository.saveOnBoardingState(complete)
    }
}