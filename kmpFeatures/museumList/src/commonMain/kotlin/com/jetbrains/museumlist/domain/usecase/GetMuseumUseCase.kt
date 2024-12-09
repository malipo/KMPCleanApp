package com.jetbrains.museumlist.domain.usecase

import com.jetbrains.museumlist.data.model.SimplePokemon
import kotlinx.coroutines.flow.Flow

interface GetMuseumUseCase {
     fun execute(): Flow<List<SimplePokemon>>
}