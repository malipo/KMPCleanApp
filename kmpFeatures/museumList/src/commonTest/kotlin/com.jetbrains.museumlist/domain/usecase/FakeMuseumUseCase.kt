package com.jetbrains.museumlist.domain.usecase

import com.jetbrains.museumlist.data.model.MuseumDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeMuseumUseCase : MuseumUseCase {
    var result: Result<List<MuseumDTO>>? = null
    override fun invoke(): Flow<Result<List<MuseumDTO>>> {
        return flow { emit(result ?: Result.failure(Exception("No result"))) }
    }
}

