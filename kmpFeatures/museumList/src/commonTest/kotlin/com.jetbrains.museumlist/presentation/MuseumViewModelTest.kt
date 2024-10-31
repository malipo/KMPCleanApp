package com.jetbrains.museumlist.presentation

import com.jetbrains.museumlist.data.model.MuseumDTO
import com.jetbrains.museumlist.data.model.TestData
import com.jetbrains.museumlist.domain.usecase.FakeMuseumUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MuseumViewModelTest {

    private lateinit var fakeMuseumUseCase: FakeMuseumUseCase
    private lateinit var viewModel: MuseumViewModel

    private val fakeMuseumDTO = TestData.fakeMuseumDTO

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        fakeMuseumUseCase = FakeMuseumUseCase()
        viewModel = MuseumViewModel(fakeMuseumUseCase)
    }

    @Test
    fun `fetchMuseum returns success and updates state`() = runTest {
        // Arrange
        fakeMuseumUseCase.result = Result.success(listOf(fakeMuseumDTO))

        // Act
        viewModel.getMuseumList()

        // Assert
        advanceUntilIdle()


        assertEquals(listOf(fakeMuseumDTO), viewModel.museumUiState.value.museums)
        assertEquals(ViewStatus.SUCCESS, viewModel.museumUiState.value.viewStatus)

    }

    @Test
    fun `fetchMuseum returns failure and updates state`() = runTest {
        // Arrange
        val exception = Exception("error")
        fakeMuseumUseCase.result = Result.failure(exception)

        // Act
        viewModel.getMuseumList()

        // Assert
        advanceUntilIdle()

        assertEquals(false, viewModel.museumUiState.value.isLoading)
        assertEquals(emptyList<MuseumDTO>(), viewModel.museumUiState.value.museums)
        assertEquals(ViewStatus.FAILED, viewModel.museumUiState.value.viewStatus)
        assertEquals("error", viewModel.museumUiState.value.message)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }
}