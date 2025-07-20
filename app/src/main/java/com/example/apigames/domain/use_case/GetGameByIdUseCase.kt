package com.example.apigames.domain.use_case

import com.example.apigames.core.common.Resource
import com.example.apigames.domain.model.GameDetail
import com.example.apigames.domain.repository.GameRepository
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(private val repository: GameRepository) {
    suspend operator fun invoke(id: Int): Resource<GameDetail>{
        return repository.getGameByID(id)
    }
}