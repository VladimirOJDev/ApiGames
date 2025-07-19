package com.example.apigames.data.mapper

import com.example.apigames.data.remote.api.GameList
import com.example.apigames.domain.model.Game

fun GameList.toDomain(): Game{
    return Game(
        id = this.id,
        name = this.name,
        backgroundImage = this.background_image,
    )
}