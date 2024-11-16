package com.jetbrains.museumlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.museumlist.data.model.SimplePokemon
import com.jetbrains.museumlist.domain.usecase.AddMuseumUseCase
import com.jetbrains.museumlist.domain.usecase.GetMuseumUseCase
import com.jetbrains.museumlist.domain.usecase.MuseumUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MuseumViewModel(private val museumUseCase: MuseumUseCase,
                      private val getMuseumUseCase: GetMuseumUseCase,
                      private val addMuseumUseCase: AddMuseumUseCase
) : ViewModel() {


    private val _museumUiState = MutableStateFlow(MuseumUiState())
    val museumUiState: StateFlow<MuseumUiState> = _museumUiState

    init {
        getMuseumList()
        println("MuseumViewModel initialized.")

        // Log the current bookmark IDs on startup
        viewModelScope.launch {
            bookmarkIds.collect { ids ->
                println("Initial Bookmark IDs: $ids")
            }
        }

        // Simulate adding a museum to bookmarks
        viewModelScope.launch {
            val museum = SimplePokemon(id = "123", name = "Museum of Art")
            add(museum)

            // Log after adding
            println("After adding museum: ${museum.id}")
        }
    }


    fun getMuseumList() {
        viewModelScope.launch {
            val result = museumUseCase.invoke().first()

            result.fold(
                onSuccess = { museumList ->
                    _museumUiState.update {
                        it.copy(
                            isLoading = false,
                            museums = museumList,
                            viewStatus = if (museumList.isEmpty()) ViewStatus.EMPTY_STATE else ViewStatus.SUCCESS
                        )
                    }
                },
                onFailure = { exception ->
                    _museumUiState.update {
                        it.copy(
                            isLoading = false,
                            museums = emptyList(),
                            viewStatus = ViewStatus.FAILED,
                            message = exception.message ?: "An error occurred"
                        )
                    }
                }
            )
        }
    }

    val bookmarkIds: StateFlow<List<String>> = getMuseumUseCase.execute()
        .map { bookmarks -> bookmarks.map { it.id } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun add(pokemon: SimplePokemon) {
        viewModelScope.launch {
            addMuseumUseCase.execute(pokemon)
        }
    }
}