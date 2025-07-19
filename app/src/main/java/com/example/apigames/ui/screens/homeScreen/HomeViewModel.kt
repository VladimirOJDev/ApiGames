package com.example.apigames.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apigames.data.repository.GamesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: GamesRepositoryImpl): ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState= _uiState.asStateFlow()

    init {
        fetchGames()
    }

    private fun fetchGames(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = repository.getGames()
                _uiState.value = _uiState.value.copy(
                    listGames = result?: emptyList()
                )
            }
        }
    }
}