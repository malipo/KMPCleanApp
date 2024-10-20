package com.jetbrains.museumlist.di

import com.jetbrains.museumlist.data.repository.MuseumRepositoryImpl
import com.jetbrains.museumlist.data.source.MuseumRemoteSource
import com.jetbrains.museumlist.data.source.MuseumRemoteSourceImlp
import com.jetbrains.museumlist.domain.MuseumRepository
import com.jetbrains.museumlist.domain.usecase.MuseumUseCase
import com.jetbrains.museumlist.domain.usecase.MuseumUseCaseImpl
import com.jetbrains.museumlist.presentation.MuseumViewModel
import com.jetbrains.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single<MuseumRemoteSource> { MuseumRemoteSourceImlp(get()) }
    single<MuseumRepository> { MuseumRepositoryImpl(get()) }
    single<MuseumUseCase> { MuseumUseCaseImpl(get()) }

}

val viewModelModule = module {
    factoryOf(::MuseumViewModel)
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
            networkModule
        )
    }
}
