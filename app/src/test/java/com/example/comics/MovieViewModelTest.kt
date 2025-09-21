package com.example.comics

import com.example.comics.domain.model.Movie
import com.example.comics.domain.usecase.GetMoviesUseCase
import com.example.comics.presentation.movies.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getMoviesUseCase = mock()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `quando useCase retorna filmes, uiState deve conter lista`() = runTest {
        // Arrange
        val fakeMovies = listOf(
            Movie(1, "Titulo A", "Desc A", "/posterA"),
            Movie(2, "Titulo B", "Desc B", "/posterB")
        )
        whenever(getMoviesUseCase()).thenReturn(fakeMovies)

        viewModel = MovieViewModel(getMoviesUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(fakeMovies, viewModel.uiState.value.movies)
        assertEquals(false, viewModel.uiState.value.isLoading)
    }

    @Test
    fun `quando useCase lança excecao, uiState deve ter lista vazia`() = runTest {
        // Arrange
        whenever(getMoviesUseCase()).thenThrow(RuntimeException("Erro"))

        viewModel = MovieViewModel(getMoviesUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(emptyList<Movie>(), viewModel.uiState.value.movies)
        assertEquals(false, viewModel.uiState.value.isLoading)
    }
}
