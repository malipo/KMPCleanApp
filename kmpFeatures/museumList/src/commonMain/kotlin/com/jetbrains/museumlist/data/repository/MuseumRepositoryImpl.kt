package com.jetbrains.museumlist.data.repository

import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.museumlist.data.model.SimplePokemon
import com.jetbrains.museumlist.data.source.LocalDatasource
import com.jetbrains.museumlist.data.source.MuseumRemoteSource
import com.jetbrains.museumlist.domain.MuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class MuseumRepositoryImpl(private val museumRemoteSource: MuseumRemoteSource,  private val localDatasource: LocalDatasource) : MuseumRepository {

    override fun getMuseum(): Flow<Result<List<MuseumDTO>>> {
        return museumRemoteSource.getMuseumList()
    }

    override fun getBookmarks(): Flow<List<SimplePokemon>> {
        return localDatasource.getAll()
    }

    override suspend fun bookmark(simplePokemon: SimplePokemon): Result<Unit> {
        val bookmarks = localDatasource.getAll().first()
        if (bookmarks.contains(simplePokemon)) {
            localDatasource.delete(simplePokemon)
        } else {
            localDatasource.upsert(simplePokemon)
        }
        return Result.success(Unit)
    }
}