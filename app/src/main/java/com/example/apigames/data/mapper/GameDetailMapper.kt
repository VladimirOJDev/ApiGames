package com.example.apigames.data.mapper

import com.example.apigames.data.remote.api.SingleGame
import com.example.apigames.domain.model.GameDetail

fun SingleGame.toDomain(): GameDetail{
    return GameDetail(
        name = this.name,
        descriptionRaw = this.description_raw ,
        metaCritic = this.metacritic ,
        website = this.website ,
        backgroundImage = this.background_image ,
    )
}