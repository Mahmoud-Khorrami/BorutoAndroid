package com.mahapp1397.borutoapp.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahapp1397.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val useCases: UseCases): ViewModel()
{
    fun saveOnBoardingState(complete: Boolean)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            useCases.saveOnBoardingUseCase(complete)
        }
    }
}