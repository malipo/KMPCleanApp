package com.jetbrains.museumlist.presentation

import com.jetbrains.museumlist.data.model.MuseumDTO

data class MuseumUiState(
    val isLoading: Boolean = true,
    val museums: List<MuseumDTO> = emptyList(),
    val viewStatus: ViewStatus = ViewStatus.LOADING,
    val message: String? = null
)