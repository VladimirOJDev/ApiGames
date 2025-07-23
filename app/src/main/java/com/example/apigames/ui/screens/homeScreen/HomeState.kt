package com.example.apigames.ui.screens.homeScreen

import com.example.apigames.domain.model.Game

data class HomeState(
    val listGames: List<Game> =  emptyList()
)
