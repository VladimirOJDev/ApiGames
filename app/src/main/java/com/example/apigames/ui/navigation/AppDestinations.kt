package com.example.apigames.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppDestinations{

    @Serializable
    object HomeView: AppDestinations()

    @Serializable
    data class DetailView(val id: Int? = null): AppDestinations()

    @Serializable
    object SearchGameView: AppDestinations()
}