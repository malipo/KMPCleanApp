package com.jetbrains.museumlist.data.source

import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.museumlist.data.model.SimplePokemon
import kotlinx.coroutines.flow.Flow

interface MuseumRemoteSource {

    fun getMuseumList(): Flow<Result<List<MuseumDTO>>>

}
