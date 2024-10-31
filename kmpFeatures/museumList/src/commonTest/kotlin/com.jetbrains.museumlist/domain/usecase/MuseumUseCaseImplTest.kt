package com.jetbrains.museumlist.domain.usecase

import com.jetbrains.museumlist.data.model.TestData
import com.jetbrains.museumlist.data.repository.FakeMuseumRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MuseumUseCaseImplTest {

    private val fakeMuseumList = listOf(
        TestData.fakeMuseumDTO
    )

    @Test
    fun `test invoke returns successful result`() = runTest {
        // Arrange
        val fakeUseCase = FakeMuseumUseCase()
        fakeUseCase.result = Result.success(fakeMuseumList)

        // Act
        val emissions = fakeUseCase.invoke().toList()

        // Assert
        assertTrue(emissions.isNotEmpty())
        assertTrue(emissions[0].isSuccess)
        assertEquals(fakeMuseumList, emissions[0].getOrNull())
    }

    @Test
    fun `test invoke returns failure result`() = runTest {
        // Arrange
        val exception = Exception("Data fetch error")
        val fakeUseCase = FakeMuseumUseCase()
        fakeUseCase.result = Result.failure(exception)

        // Act
        val emissions = fakeUseCase.invoke().toList()

        // Assert
        assertTrue(emissions.isNotEmpty())
        assertTrue(emissions[0].isFailure)
        assertEquals(exception, emissions[0].exceptionOrNull())
    }
}