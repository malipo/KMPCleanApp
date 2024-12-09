package com.jetbrains.tv.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.tv.data.MovieRepository
import com.jetbrains.tv.data.model.Genre
import kotlinx.coroutines.launch

data class HomeScreenUIState(val genres: List<Genre>)


class HomeScreenViewModel (private val movieRepository: MovieRepository) :
    ViewModel() {

    init {
        retrieveGenres()
    }

    private val _uiState = mutableStateOf(HomeScreenUIState(listOf()))
    val uiState: State<HomeScreenUIState>
        get() = _uiState

    private fun retrieveGenres() {
        viewModelScope.launch {
            val genres = movieRepository.retrieveGenres()
            _uiState.value = HomeScreenUIState(genres)
        }
    }
}