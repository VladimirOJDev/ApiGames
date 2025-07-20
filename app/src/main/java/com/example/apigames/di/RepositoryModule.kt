package com.example.apigames.di

import com.example.apigames.data.remote.api.ApiGame
import com.example.apigames.data.repository.GamesRepositoryImpl
import com.example.apigames.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesGameRepository(apiGame: ApiGame): GameRepository{
        return GamesRepositoryImpl(apiGame)
    }
}