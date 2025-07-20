package com.example.apigames.ui.screens.detailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apigames.core.common.Resource
import com.example.apigames.domain.use_case.GetGameByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameByIdUseCase: GetGameByIdUseCase): ViewModel() {

    private val _uiStateDetail = MutableStateFlow(DetailState())
     val uiStateDetail = _uiStateDetail.asStateFlow()

    fun getGameById(id: Int){
        viewModelScope.launch {
            val result = gameByIdUseCase(id)

            when(result){
                is Resource.Success ->{
                    _uiStateDetail.value = _uiStateDetail.value.copy(
                        name = result.data?.name ?: "null",
                        descriptionRaw = result.data?.descriptionRaw ?: "",
                        metaCritic = result.data?.metaCritic ?: 111, //llega hasta el 100 entonces 111 no tiene calificacion
                        website = result.data?.website?: "Sin WebSite",
                        backgroundImage = result.data?.backgroundImage?:""

                    )
                }
                is Resource.Error ->{}
                else -> {}
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