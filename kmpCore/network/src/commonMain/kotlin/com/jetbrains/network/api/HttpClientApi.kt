package com.jetbrains.network.api

import io.ktor.client.HttpClient

interface HttpClientApi {
    fun getHttpClient(): HttpClient

}