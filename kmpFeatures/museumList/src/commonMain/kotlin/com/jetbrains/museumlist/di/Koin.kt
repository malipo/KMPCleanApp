package com.jetbrains.museumlist.di

import com.jetbrains.museumlist.data.repository.MuseumRepositoryImpl
import com.jetbrains.museumlist.data.source.LocalDatasource
import com.jetbrains.museumlist.data.source.LocalDatasourceImpl
import com.jetbrains.museumlist.data.source.MuseumRemoteSource
import com.jetbrains.museumlist.data.source.MuseumRemoteSourceImlp
import com.jetbrains.museumlist.domain.MuseumRepository
import com.jetbrains.museumlist.domain.usecase.AddMuseumUseCase
import com.jetbrains.museumlist.domain.usecase.AddMuseumUseCaseImpl
import com.jetbrains.museumlist.domain.usecase.GetMuseumUseCase
import com.jetbrains.museumlist.domain.usecase.GetMuseumUseCaseImpl
import com.jetbrains.museumlist.domain.usecase.MuseumUseCase
import com.jetbrains.museumlist.domain.usecase.MuseumUseCaseImpl
import com.jetbrains.museumlist.presentation.MuseumViewModel
import com.jetbrains.network.SharedViewModel.SharedViewModel
import com.jetbrains.network.di.networkModule
import com.jetbrains.room.database.PeopleDatabase
import com.jetbrains.room.di.roomModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<MuseumRemoteSource> { MuseumRemoteSourceImlp(get()) }
    single<LocalDatasource> { LocalDatasourceImpl(get()) }

    single<MuseumRepository> { MuseumRepositoryImpl(get(),get()) }
    single<MuseumUseCase> { MuseumUseCaseImpl(get()) }

    factory<AddMuseumUseCase> { AddMuseumUseCaseImpl(get()) }
    factory<GetMuseumUseCase> { GetMuseumUseCaseImpl(get()) }

}

val viewModelModule = module {
    factoryOf(::MuseumViewModel)
    viewModel { MuseumViewModel(get()) }
}


val sharedViewModelModule = module {
    SharedViewModel { MuseumViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
            sharedViewModelModule,
            networkModule,
            roomModule
        )
    }
}
