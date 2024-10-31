package com.jetbrains.museumlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.museumlist.domain.usecase.MuseumUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MuseumViewModel(private val museumUseCase: MuseumUseCase) : ViewModel() {

    private val _museumUiState = MutableStateFlow(MuseumUiState())
    val museumUiState: StateFlow<MuseumUiState> = _museumUiState

    init {
        getMuseumList()
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
}