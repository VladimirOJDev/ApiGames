package com.example.apigames.data.repository

import com.example.apigames.data.remote.api.ApiGame
import com.example.apigames.data.remote.api.GameList
import com.example.apigames.data.remote.api.SingleGame
import javax.inject.Inject

//Esta clase es responsable de obtener los datos de los juegos desde fuentes remotas (API).

class GamesRepositoryImpl @Inject constructor(private val apiGames: ApiGame) {

    suspend fun getGames(): List<GameList>? {
        val response = apiGames.getGames() // Ejecuta la llamada a la API.
        if (response.isSuccessful) { // Comprueba si la respuesta HTTP fue exitosa (códigos 2xx).
            return response.body()?.results // Extrae la lista de juegos del cuerpo de la respuesta.
        }
        return null // Retorna null si la respuesta de la API no fue exitosa.
    }

    suspend fun getGameByID(id: Int): SingleGame?{
        val response = apiGames.getGameById(id)

        if (response.isSuccessful) { // Comprueba si la respuesta HTTP fue exitosa (códigos 2xx).
            return response.body() // Extrae la lista de juegos del cuerpo de la respuesta.
        }
        return null
    }
}