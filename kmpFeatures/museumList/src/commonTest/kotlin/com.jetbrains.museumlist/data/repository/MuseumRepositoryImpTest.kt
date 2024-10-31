package com.jetbrains.museumlist.data.repository

import com.jetbrains.museumlist.data.model.TestData
import com.jetbrains.museumlist.data.source.FakeMuseumRemoteSource
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MuseumRepositoryImplTest {

    private val fakeMuseumList = listOf(
        TestData.fakeMuseumDTO
    )

    @Test
    fun `test getMuseum returns successful result`() = runTest {
        // Arrange
        val fakeRemoteSource = FakeMuseumRemoteSource(Result.success(fakeMuseumList))
        val repository = MuseumRepositoryImpl(fakeRemoteSource)

        // Act
        val emissions = repository.getMuseum().toList()

        // Assert
        assertTrue(emissions.isNotEmpty())
        assertTrue(emissions[0].isSuccess)
        assertEquals(fakeMuseumList, emissions[0].getOrNull())
    }

    @Test
    fun `test getMuseum returns failure result`() = runTest {
        // Arrange
        val exception = Exception("Network error")
        val fakeRemoteSource = FakeMuseumRemoteSource(Result.failure(exception))
        val repository = MuseumRepositoryImpl(fakeRemoteSource)

        // Act
        val emissions = repository.getMuseum().toList()

        // Assert
        assertTrue(emissions.isNotEmpty())
        assertTrue(emissions[0].isFailure)
        assertEquals(exception, emissions[0].exceptionOrNull())
    }
}