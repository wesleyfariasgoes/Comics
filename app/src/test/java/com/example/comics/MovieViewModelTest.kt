package com.example.comics

import com.example.comics.domain.model.Movie
import com.example.comics.domain.usecase.GetMoviesUseCase
import com.example.comics.presentation.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getMoviesUseCase = mock()
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `inicializacao do ViewModel busca filmes e atualiza o estado`() = runTest {
        // Arrange
        val movies = listOf(
            Movie(1, "Movie 1", "Overview 1", "poster1.jpg"),
            Movie(2, "Movie 2", "Overview 2", "poster2.jpg")
        )
        whenever(getMoviesUseCase(any())).thenReturn(movies)

        // Act
        viewModel = MovieViewModel(getMoviesUseCase)

        // Assert
        assertFalse(viewModel.isLoading)
        assertFalse(viewModel.endOfList)
        assertEquals(movies, viewModel.movies)
    }

    @Test
    fun `falha na busca de filmes nao atualiza a lista e define isLoading como false`() = runTest {
        // Arrange
        whenever(getMoviesUseCase(any())).thenThrow(RuntimeException("API Error"))

        // Act
        viewModel = MovieViewModel(getMoviesUseCase)

        // Assert
        assertFalse(viewModel.isLoading)
        assertTrue(viewModel.movies.isEmpty())
        assertFalse(viewModel.endOfList)
    }

    @Test
    fun `fetchMovies carrega mais filmes e atualiza a lista de forma incremental`() = runTest {
        // Arrange - Primeiro carregamento
        val initialMovies = listOf(Movie(1, "Movie 1", "Overview 1", "poster1.jpg"))
        whenever(getMoviesUseCase(page = 1)).thenReturn(initialMovies)
        viewModel = MovieViewModel(getMoviesUseCase)
        assertEquals(1, viewModel.movies.size)

        // Arrange - Segundo carregamento
        val moreMovies = listOf(Movie(2, "Movie 2", "Overview 2", "poster2.jpg"))
        whenever(getMoviesUseCase(page = 2)).thenReturn(moreMovies)
        viewModel.fetchMovies()

        // Assert
        assertFalse(viewModel.isLoading)
        assertFalse(viewModel.endOfList)
        assertEquals(2, viewModel.movies.size)
        assertEquals(initialMovies + moreMovies, viewModel.movies)
    }

    @Test
    fun `fetchMovies define endOfList como true quando a lista de filmes retorna vazia`() = runTest {
        // Arrange - Primeiro carregamento com filmes
        val movies = listOf(Movie(1, "Movie 1", "Overview 1", "poster1.jpg"))
        whenever(getMoviesUseCase(page = 1)).thenReturn(movies)
        viewModel = MovieViewModel(getMoviesUseCase)
        assertEquals(1, viewModel.movies.size)
        assertFalse(viewModel.endOfList)

        // Arrange - Segundo carregamento, simulando o fim da lista
        whenever(getMoviesUseCase(page = 2)).thenReturn(emptyList())
        viewModel.fetchMovies()

        // Assert
        assertFalse(viewModel.isLoading)
        assertTrue(viewModel.endOfList)
    }

    @Test
    fun `nao deve buscar filmes se isLoading for true`() = runTest {
        // Arrange
        viewModel = MovieViewModel(getMoviesUseCase)
        viewModel.isLoading = true
        val initialMovies = viewModel.movies.size

        // Act
        viewModel.fetchMovies()

        // Assert
        assertEquals(initialMovies, viewModel.movies.size)
    }

    @Test
    fun `nao deve buscar filmes se endOfList for true`() = runTest {
        // Arrange
        viewModel = MovieViewModel(getMoviesUseCase)
        viewModel.endOfList = true
        val initialMovies = viewModel.movies.size

        // Act
        viewModel.fetchMovies()

        // Assert
        assertEquals(initialMovies, viewModel.movies.size)
    }
}
