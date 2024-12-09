package com.jetbrains.museumlist.domain.usecase

import com.jetbrains.museumlist.data.model.SimplePokemon
import com.jetbrains.museumlist.domain.MuseumRepository

class AddMuseumUseCaseImpl(
    private val repository: MuseumRepository
) : AddMuseumUseCase {

    override suspend fun execute(simplePokemon: SimplePokemon): Result<Unit> {
        return repository.bookmark(simplePokemon)
    }
}