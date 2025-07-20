package com.example.apigames.domain.use_case

import com.example.apigames.core.common.Resource
import com.example.apigames.domain.model.Game
import com.example.apigames.domain.repository.GameRepository
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(): Resource<List<Game>>{
        return repository.getGames()
    }
}