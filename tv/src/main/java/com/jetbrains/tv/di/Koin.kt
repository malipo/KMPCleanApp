package com.jetbrains.tv.di


import com.jetbrains.tv.data.MovieRepository
import com.jetbrains.tv.data.MovieRepositoryImpl
import com.jetbrains.tv.presentation.DetailsScreenViewModel
import com.jetbrains.tv.presentation.HomeScreenViewModel
import com.jetbrains.tv.data.util.GenreJsonParser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<GenreJsonParser> { GenreJsonParser(get()) }

}

val viewModelModule = module {
    viewModel { HomeScreenViewModel(get()) }
    viewModel { DetailsScreenViewModel(get()) }
}


fun initKoin() {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
        )
    }
}
