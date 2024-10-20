package com.jetbrains.museumlist.domain

import com.jetbrains.museumlist.data.model.MuseumDTO
import kotlinx.coroutines.flow.Flow

interface MuseumRepository {
    fun getMuseum(): Flow<Result<List<MuseumDTO>>>
}
