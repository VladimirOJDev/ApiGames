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
import com.example.apigames.domain.use_case.GetGameByIdUseCase

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGameUseCase: GetGamesUseCase,
    private val getGameByIdUseCase:GetGameByIdUseCase

): ViewModel(){

    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

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

    fun getGameById(id: Int){
        viewModelScope.launch {
            val result = getGameByIdUseCase(id)

            when(result){
                is Resource.Success ->{
                    _uiState.value = _uiState.value.copy(
                        name = result.data?.name ?: "null",
                        descriptionRaw = result.data?.descriptionRaw ?: "",
                        metaCritic = result.data?.metaCritic ?: 111, //llega hasta el 100 entonces 111 no tiene calificacion
                        website = result.data?.website?: "Sin WebSite",
                        backgroundImage = result.data?.backgroundImage?:"",
                        id = id

                    )
                }
                is Resource.Error ->{}
                else -> {}
            }
        }
    }

    fun cleanState(){
        _uiState.value = _uiState.value.copy(
            name = "",
            descriptionRaw =  "",
            metaCritic = 111,
            website = "",
            backgroundImage = "",
            id = null
        )
    }

    fun upDateIdState(id: Int){
        _uiState.value = _uiState.value.copy(id = id)
    }
}