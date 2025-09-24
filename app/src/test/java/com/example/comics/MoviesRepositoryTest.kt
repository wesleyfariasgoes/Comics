package com.example.comics.data.repository

import com.example.comics.data.api.Api
import com.example.comics.data.model.MovieDto
import com.example.comics.data.model.MovieResponse
import com.example.comics.data.model.PaginatedMovies
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class MoviesRepositoryTest {

    private lateinit var api: Api
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        api = mock()
        repository = MovieRepositoryImpl(api)
    }

    @Test
    fun `getMovies retorna PaginatedMovies quando sucesso`() = runTest {
        // Arrange
        val movieDto = MovieDto(
            id = 1,
            title = "Titulo de Teste",
            overview = "Descricao",
            posterPath = "/poster.jpg"
        )
        val response = MovieResponse(results = listOf(movieDto), totalPages = 5, totalResults = 10, page = 1)

        // Simula o retorno direto da sua classe MovieResponse
        whenever(api.getDay(any(), any())).thenReturn(response)

        val result: PaginatedMovies = repository.getMovies(page = 1)

        // Assert
        assertEquals(1, result.movies.size)
        assertEquals(5, result.totalPages)
        assertEquals("Titulo de Teste", result.movies[0].title)
        assertEquals("Descricao", result.movies[0].overview)
        assertEquals("https://image.tmdb.org/t/p/w500/poster.jpg", result.movies[0].posterPath)
    }

    @Test
    fun `getMovies lanca excecao quando a chamada da API falha`() = runTest {
        // Arrange
        whenever(api.getDay(any(), any())).thenThrow(RuntimeException("Erro na API"))

        // Assert
        assertThrows(RuntimeException::class.java) {
            runBlocking {
                repository.getMovies(page = 1)
            }
        }
    }
}
