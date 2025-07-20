package com.example.apigames.data.repository

import com.example.apigames.core.common.Resource
import com.example.apigames.data.mapper.toDomain
import com.example.apigames.data.remote.api.ApiGame
import com.example.apigames.domain.model.Game
import com.example.apigames.domain.model.GameDetail
import com.example.apigames.domain.repository.GameRepository
import javax.inject.Inject

//Esta clase es responsable de obtener los datos de los juegos desde fuentes remotas (API).

class GamesRepositoryImpl @Inject constructor(private val apiGames: ApiGame): GameRepository {

    override suspend fun getGames(): Resource<List<Game>> { //La clase Resource retorna un tipo de dato generico en este cao una list
        return try {
            val response = apiGames.getGames() // Ejecuta la llamada a la API.
            if (response.isSuccessful) { // Comprueba si la respuesta HTTP fue exitosa (códigos 2xx).
                val games = response.body()?.results?.map { it.toDomain() }?: emptyList() //Mapear al modelo de dominio
               Resource.Success(games) // Retorna la lista de juegos del cuerpo de la respuesta.
            }else {
                Resource.Error("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.localizedMessage ?: "Unknown error"}")
        }
    }

    override suspend fun getGameByID(id: Int): Resource<GameDetail>{

        return try {
            val response = apiGames.getGameById(id)

            if (response.isSuccessful) { // Comprueba si la respuesta HTTP fue exitosa (códigos 2xx).
                val gameId = response.body()?.toDomain() // Extrae la lista de juegos del cuerpo de la respuesta.
                if (gameId != null) {
                    Resource.Success(gameId)
                }else{
                    Resource.Error("Game not found")
                }
            }else{
                Resource.Error("Error: ${response.code()}") //Nos devuelde el error que ocurrio
            }
        } catch (e: Exception) {
            Resource.Error("Exception: ${e.localizedMessage ?: "Unknown error"}") //Algo salio mal
        }

    }
}