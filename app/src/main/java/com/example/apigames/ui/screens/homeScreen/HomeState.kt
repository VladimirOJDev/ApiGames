package com.example.apigames.ui.screens.homeScreen

import com.example.apigames.domain.model.Game

data class HomeState(
    val listGames: List<Game> =  emptyList(),
    val name:String = "",
    val descriptionRaw: String= "",
    val metaCritic: Int = 0,
    val website: String = "",
    val backgroundImage: String = "",
)
