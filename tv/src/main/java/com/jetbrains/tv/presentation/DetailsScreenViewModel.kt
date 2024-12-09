package com.jetbrains.tv.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jetbrains.tv.data.MovieRepository
import com.jetbrains.tv.data.model.Movie

data class DetailsScreeUIState(val movie: Movie?)

class DetailsScreenViewModel(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _uiState = mutableStateOf(DetailsScreeUIState(null))
    val uiState: State<DetailsScreeUIState>
        get() = _uiState

    suspend fun retrieveMovie(id: Int) {
        val movie = movieRepository.retrieveMovie(id)
        _uiState.value = DetailsScreeUIState(movie)
    }
}