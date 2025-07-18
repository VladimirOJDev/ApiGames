
package com.example.apigames.data.remote

import com.example.apigames.BuildConfig


class Constants {
    companion object{
        //Url base de donde vamos a recuperar los datos de internet
        const val BASE_URL = "https://api.rawg.io/api/"
        const val ENDPOINT = "games" //parte final de la ruta
        const val API_KEY = BuildConfig.API_KEY //Llave que nos proporciona la pàgina para pedir datos desde su sitio

    }
}

