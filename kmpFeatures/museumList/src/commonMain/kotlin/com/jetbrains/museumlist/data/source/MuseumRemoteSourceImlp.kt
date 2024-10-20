package com.jetbrains.museumlist.data.source

import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.network.api.APIs
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MuseumRemoteSourceImlp(private val httpClient: HttpClient) : MuseumRemoteSource {
    override fun getMuseumList(): Flow<Result<List<MuseumDTO>>> = flow {
        try {
            val museumList: List<MuseumDTO> = httpClient.get(APIs.GET_MUSEUM).body()
            emit(Result.success(museumList))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }
}