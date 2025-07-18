package com.example.apigames.di

import com.example.apigames.data.remote.Constants.Companion.BASE_URL
import com.example.apigames.data.remote.api.ApiGame
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
/**
 * Módulo Hilt para proveer dependencias a nivel de aplicación.
 *
 * `@Module` lo marca como un módulo Hilt.
 * `@InstallIn(SingletonComponent::class)` indica que las dependencias provistas
 * estarán disponibles durante toda la vida de la aplicación (alcance Singleton).
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule { // Se usa un 'object' para que sea un Singleton por defecto

    /**
     * Provee una instancia Singleton de Retrofit.
     *
     * `@Singleton` asegura que solo se cree una instancia de Retrofit en toda la app.
     * `@Provides` indica a Hilt que este mét-odo sabe cómo crear un objeto Retrofit.
     *
     * @return Una instancia configurada de [Retrofit].
     */

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)// Establece la URL base para todas las llamadas de la API
            .addConverterFactory(GsonConverterFactory.create()) // Usa Gson para convertir JSON
            .build()
    }

    /**
     * Provee una instancia Singleton de la interfaz [ApiGame].
     *
     * `@Singleton` asegura que solo se cree una instancia de ApiGame.
     * `@Provides` indica a Hilt que este método sabe cómo crear ApiGame.
     *
     * @param retrofit La instancia de [Retrofit] que será inyectada automáticamente por Hilt.
     * @return Una implementación de [ApiGame] creada por Retrofit.
     */

    @Singleton
    @Provides
    fun providesApiGames(retrofit: Retrofit): ApiGame{
        return retrofit.create(ApiGame::class.java)
    }
}