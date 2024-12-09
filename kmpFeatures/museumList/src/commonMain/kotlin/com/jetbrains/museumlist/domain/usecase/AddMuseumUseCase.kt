package com.jetbrains.museumlist.domain.usecase

import com.jetbrains.museumlist.data.model.SimplePokemon

interface AddMuseumUseCase {
     suspend fun execute(simplePokemon: SimplePokemon): Result<Unit>
}