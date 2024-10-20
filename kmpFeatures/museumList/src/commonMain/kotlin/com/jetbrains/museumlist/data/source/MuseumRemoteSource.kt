package com.jetbrains.museumlist.data.source

import com.jetbrains.museumlist.data.model.MuseumDTO
import kotlinx.coroutines.flow.Flow

interface MuseumRemoteSource {

    fun getMuseumList(): Flow<Result<List<MuseumDTO>>>
}