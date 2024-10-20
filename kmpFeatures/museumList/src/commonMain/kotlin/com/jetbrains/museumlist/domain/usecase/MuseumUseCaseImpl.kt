package com.jetbrains.museumlist.domain.usecase

import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.museumlist.domain.MuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MuseumUseCaseImpl(private val museumRepository: MuseumRepository) : MuseumUseCase {
    override  fun invoke(): Flow<Result<List<MuseumDTO>>> {
        return flow {
            museumRepository.getMuseum().collect { response ->
                response.fold(
                    onSuccess = { museumList ->
                        emit(Result.success(museumList))
                    },
                    onFailure = { exception ->
                        emit(Result.failure(exception))
                    }
                )
            }
        }
    }
}