package com.example.apigames.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apigames.domain.use_case.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.apigames.core.common.Resource

@HiltViewModel
class HomeViewModel @Inject constructor(private val getGameUseCase: GetGamesUseCase): ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState= _uiState.asStateFlow()

    init {
        fetchGames()
    }

    private fun fetchGames(){
        viewModelScope.launch {
            val result = getGameUseCase()

            when (result) {
                is Resource.Success -> {
                    _uiState.value =  _uiState.value.copy(listGames = result.data?: emptyList())
                }
                is Resource.Error -> {

                }
                else -> {}
                // Resource.Loading podría no ser necesario aquí si es una operación inmediata
            }
        }
    }
}