package com.mahapp1397.borutoapp.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahapp1397.borutoapp.domain.models.Hero
import com.mahapp1397.borutoapp.domain.use_cases.UseCases
import com.mahapp1397.borutoapp.utils.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle): ViewModel()
{
    private val _selectedHero : MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero : StateFlow<Hero?> = _selectedHero

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette : State<Map<String, String>> = _colorPalette

    init
    {
        viewModelScope.launch(Dispatchers.IO){

            val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)

            _selectedHero.value = heroId?.let { useCases.getSelectedHeroUseCase(heroId) }
        }
    }

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent : SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    fun generateColorPalette(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.generateColorPalett)
        }
    }

    fun setColorPalette(colors: Map<String, String>)
    {
        _colorPalette.value = colors

    }
}

sealed class UiEvent{
    object generateColorPalett: UiEvent()
}