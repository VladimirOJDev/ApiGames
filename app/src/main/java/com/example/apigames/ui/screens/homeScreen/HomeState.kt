package com.example.apigames.ui.screens.homeScreen

import com.example.apigames.data.remote.api.GameList

data class HomeState(
    val listGames: List<GameList> =  emptyList()
)
