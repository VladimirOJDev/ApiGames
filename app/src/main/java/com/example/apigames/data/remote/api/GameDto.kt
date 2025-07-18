package com.example.apigames.data.remote.api


//Clases donde se contendran lo que viene de la api es crucial que los nombres
// de las variables contenga el mismo nombre y el mismo tipo de dato que el de la api
//de lo contario se generarán errores


data class GameDto(
    val count: Int,
    val results: List<GameList>
)


data class GameList(
    val id: Int,
    val name: String,
    val background_image: String,
)

data class SingleGame(
    val name:String,
    val description_raw: String,
    val metacritic: Int,
    val website: String,
    val background_image: String,
)
