package com.jetbrains.museumlist.domain

import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.museumlist.data.model.SimplePokemon
import kotlinx.coroutines.flow.Flow

interface MuseumRepository {
    fun getMuseum(): Flow<Result<List<MuseumDTO>>>

    fun getBookmarks(): Flow<List<SimplePokemon>>
    suspend fun bookmark(simplePokemon: SimplePokemon): Result<Unit>
}
