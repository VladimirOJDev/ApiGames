package com.example.apigames.domain.repository

import com.example.apigames.core.common.Resource
import com.example.apigames.domain.model.Game
import com.example.apigames.domain.model.GameDetail
import kotlinx.coroutines.flow.Flow

//Interfaz para la capa de dominio
interface GameRepository {
    //obtiene una lista de juegos o un estado de error/carga.
    fun getGames(): Flow<Resource<List<Game>>>

    //obtiene los detalles de un juego por su id o un estado de error/carga.
    fun getGameByID(gameId: Int): Flow<Resource<GameDetail>>

}