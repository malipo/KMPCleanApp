package com.jetbrains.network.di

import com.jetbrains.network.SharedViewModel.SharedViewModel
import com.jetbrains.network.api.Network
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single<HttpClient> {
        Network().httpClient
    }
}
