package com.jetbrains.museumlist.data.repository

import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.museumlist.domain.MuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


class FakeMuseumRepository : MuseumRepository {
    var result: Result<List<MuseumDTO>>? = null
    override fun getMuseum(): Flow<Result<List<MuseumDTO>>> {
        return flow { emit(result?:Result.failure(Exception("error"))) }
    }
}

