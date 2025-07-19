package com.example.apigames.ui.screens.detailScreen

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
class DetailViewModel @Inject constructor(private val repository: GamesRepositoryImpl): ViewModel() {

    private val _uiStateDetail = MutableStateFlow(DetailState())
     val uiStateDetail = _uiStateDetail.asStateFlow()

    fun getGameById(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = repository.getGameByID(id)

                _uiStateDetail.value = _uiStateDetail.value.copy(
                    name = result?.name ?: "null",
                    descriptionRaw = result?.description_raw ?: "",
                    metaCritic = result?.metacritic ?: 111, //llega hasta el 100 entonces 111 no tiene calificacion
                    website = result?.website?: "Sin WebSite",
                    backgroundImage = result?.background_image?: ""

                )
            }
        }
    }

    fun cleanState(){
        _uiStateDetail.value = _uiStateDetail.value.copy(
            name = "",
            descriptionRaw =  "",
            metaCritic = 111,
            website = "",
            backgroundImage = ""
        )
    }

}