package com.jetbrains.museumlist.domain.usecase

import com.jetbrains.museumlist.data.model.MuseumDTO
import kotlinx.coroutines.flow.Flow

interface MuseumUseCase {
     operator fun invoke(): Flow<Result<List<MuseumDTO>>>
}