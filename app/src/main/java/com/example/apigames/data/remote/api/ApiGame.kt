package com.example.apigames.data.remote.api

import com.example.apigames.data.remote.Constants.Companion.API_KEY
import com.example.apigames.data.remote.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz de la API de juegos.
 *
 * Esta interfaz define los métodos que Retrofit utilizará para comunicarse con la API remota.
 * Cada función suspendida corresponde a una solicitud HTTP específica a un endpoint de la API.
 */

interface ApiGame {
/**
 * Realiza una solicitud GET para obtener una lista de juegos.
 *
 * @GET indica que esta es una operación HTTP GET.
 * La URL de la solicitud se construye concatenando el [ENDPOINT] base y la [API_KEY].
 *
 */
@GET(ENDPOINT)
    suspend fun getGames(
        @Query("key") apiKey: String = API_KEY,
        @Query("page_size") pageSize: Int = 40
    ): Response<GameDto> //Funcion suspendida para llamar a  los juegos

/*
*Obtiene un jugego dependiendo su id y nos trae los daos que decalramos en SingleGame
* */
    @GET("$ENDPOINT/{id}")
    suspend fun getGameById(
        @Path(value = "id")id:Int,
        @Query("key") apiKey: String = API_KEY

    ): Response<SingleGame>
}

