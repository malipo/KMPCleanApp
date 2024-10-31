package com.jetbrains.museumlist.data.source

import com.jetbrains.museumlist.data.model.MuseumDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMuseumRemoteSource(private val fakeResult: Result<List<MuseumDTO>>) : MuseumRemoteSource {
    override fun getMuseumList(): Flow<Result<List<MuseumDTO>>> = flow {
        emit(fakeResult)
    }
}