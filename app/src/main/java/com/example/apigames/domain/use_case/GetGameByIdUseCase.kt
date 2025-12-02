package com.example.apigames.domain.use_case

import com.example.apigames.core.common.Resource
import com.example.apigames.domain.model.GameDetail
import com.example.apigames.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGameByIdUseCase @Inject constructor(private val repository: GameRepository) {
    operator fun invoke(id: Int): Flow<Resource<GameDetail>>{
        return repository.getGameByID(id)
    }
}