package com.jetbrains.museumlist.domain.usecase

import com.jetbrains.museumlist.data.model.SimplePokemon
import com.jetbrains.museumlist.domain.MuseumRepository
import kotlinx.coroutines.flow.Flow

class GetMuseumUseCaseImpl(
    private val repository: MuseumRepository
) : GetMuseumUseCase {

    override fun execute(): Flow<List<SimplePokemon>> {
        return repository.getBookmarks()
    }
}