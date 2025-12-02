package com.example.apigames.data.repository

import com.example.apigames.core.common.Resource
import com.example.apigames.data.mapper.toDomain
import com.example.apigames.data.remote.api.ApiGame
import com.example.apigames.domain.model.Game
import com.example.apigames.domain.model.GameDetail
import com.example.apigames.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//Esta clase es responsable de obtener los datos de los juegos desde fuentes remotas (API).

class GamesRepositoryImpl @Inject constructor(private val apiGames: ApiGame): GameRepository {

    override fun getGames(): Flow<Resource<List<Game>>> = flow{ //La clase Resource retorna un tipo de dato generico en este cao una list

        emit(Resource.Loading()) //Inica el proceso de carga mientras se cargan los datos o se muestra algun error

        try {
            val response = apiGames.getGames() // Ejecuta la llamada a la API.
            if (response.isSuccessful) { // Comprueba si la respuesta HTTP fue exitosa (códigos 2xx).
                val games = response.body()?.results?.map { it.toDomain() }?: emptyList() //Mapear al modelo de dominio
                emit(Resource.Success(games)) // Retorna la lista de juegos del cuerpo de la respuesta.

            }else {
                emit(
                    Resource.Error("Error: ${response.code()}") //Si ocurrio un error al cargar los datos nos mnanda de conexion caon la api
                )
            }
        } catch (e: Exception) {
            emit(
                Resource.Error("Exception: ${e.localizedMessage ?: "Unknown error"}") //Si algo ha ocurrido durante el proceso a la llamada de la api
            )
        }
    }

    override fun getGameByID(id: Int): Flow<Resource<GameDetail>> = flow{

        emit(Resource.Loading()) //Emite el estado de carga

        try {
            val response = apiGames.getGameById(id)

            if (response.isSuccessful) { // Comprueba si la respuesta HTTP fue exitosa (códigos 2xx).
                val gameId = response.body()?.toDomain() // Extrae la lista de juegos del cuerpo de la respuesta.
                if (gameId != null) {
                    emit(Resource.Success(gameId))
                }else{
                    emit(Resource.Error("Game not found"))
                }
            }else{
                emit(Resource.Error("Error: ${response.code()}")) //Nos devuelde el error que ocurrio al llamar la api
            }
        } catch (e: Exception) {
            emit(Resource.Error("Exception: ${e.localizedMessage ?: "Unknown error"}")) //Algo salio mal
        }
    }
}