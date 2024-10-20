package com.jetbrains.museumlist.data.repository

import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.museumlist.data.source.MuseumRemoteSource
import com.jetbrains.museumlist.domain.MuseumRepository
import kotlinx.coroutines.flow.Flow

class MuseumRepositoryImpl(private val museumRemoteSource: MuseumRemoteSource) : MuseumRepository {
    override fun getMuseum(): Flow<Result<List<MuseumDTO>>> {
        return museumRemoteSource.getMuseumList()
    }

}