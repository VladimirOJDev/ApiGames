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
    private var hasLoadedInitialDetail = false

    init {
        fetchGames()
    }

    //Hace una llamada getGameUseCase para obtener una lista de Games pasandolos al estado
    private fun fetchGames(){
        viewModelScope.launch {

            getGameUseCase().collect{ result -> //Emite un Flow de tipo Resource, Resource es de tipo generico
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoadingList = true)
                    }
                    is Resource.Success -> {
                        val games = result.data?: emptyList()

                        _uiState.value =  _uiState.value.copy(listGames =games ) //Devuelve una lista de Games o lista vacia y lo pasa al estado
                        _uiState.value = _uiState.value.copy(isLoadingList = false)

                        if (!hasLoadedInitialDetail && games.isNotEmpty()) { //Se ejecuta una sola vez cuuando se inicia la app, papsando el id del primer juego de la lista games
                            hasLoadedInitialDetail = true
                            getGameById(games.first().id)
                        }

                    }
                    is Resource.Error -> {}
                }
            }
        }
    }


    fun getGameById(id: Int){
        viewModelScope.launch {
            getGameByIdUseCase(id).collect{result ->

                when(result){
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoadingDetail = true)
                    }
                    is Resource.Success ->{
                        _uiState.value = _uiState.value.copy(
                            name = result.data?.name ?: "null",
                            descriptionRaw = result.data?.descriptionRaw ?: "",
                            metaCritic = result.data?.metaCritic ?: 111, //llega hasta el 100 entonces 111 no tiene calificacion
                            website = result.data?.website?: "Sin WebSite",
                            backgroundImage = result.data?.backgroundImage?:"",
                            id = id
                        )
                        _uiState.value = _uiState.value.copy(isLoadingDetail = false)
                    }
                    is Resource.Error ->{}//Manda un ensaje de error
                }
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
}