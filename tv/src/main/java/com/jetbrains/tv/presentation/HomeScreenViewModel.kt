package com.jetbrains.tv.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.tv.data.MovieRepository
import com.jetbrains.tv.data.model.Genre
import com.jetbrains.tv.data.model.Movie
import kotlinx.coroutines.launch

data class HomeScreenUIState(val genres: List<Genre>)

class HomeScreenViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = mutableStateOf(HomeScreenUIState(listOf()))
    val uiState: State<HomeScreenUIState>
        get() = _uiState

    private val _featuredMovies = mutableStateOf<List<Movie>>(emptyList())
    val featuredMovies: State<List<Movie>>
        get() = _featuredMovies

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?>
        get() = _error

    init {
        retrieveGenres()
        retrieveFeaturedMovies()
    }

    private fun retrieveGenres() {
        viewModelScope.launch {
            try {
                val genres = movieRepository.retrieveGenres()
                _uiState.value = HomeScreenUIState(genres)
            } catch (e: Exception) {
                _error.value = "Failed to load genres"
            }
        }
    }

    private fun retrieveFeaturedMovies() {
        viewModelScope.launch {
            try {
                val featured = movieRepository.retrieveFeaturedMovies()
                _featuredMovies.value = featured
            } catch (e: Exception) {
                _error.value = "Failed to load featured movies"
            }
        }
    }
}