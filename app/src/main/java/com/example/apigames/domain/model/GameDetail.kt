package com.example.apigames.domain.model

data class GameDetail (
    val name:String,
    val descriptionRaw: String,
    val metaCritic: Int,
    val website: String,
    val backgroundImage: String,
)