package com.example.comics.data.repository

import com.example.comics.data.api.Api
import com.example.comics.data.model.MovieDto
import com.example.comics.data.model.MovieResponse
import com.example.comics.domain.model.Movie
import com.example.comics.domain.repository.MovieRepository
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

class MoviesRepositoryTest {

    private lateinit var api: Api
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        api = mock()
        repository = MovieRepositoryImpl(api)
    }

    @Test
    fun `getMovies retorna lista quando sucesso`() = runTest {
        // Arrange
        val movieDto = MovieDto(
            id = 1,
            title = "Titulo de Teste",
            overview = "Descricao",
            posterPath = "/poster.jpg"
        )
        val response = MovieResponse(results = listOf(movieDto))

        whenever(api.getDay(any())).thenReturn(Response.success(response))

        val result: List<Movie> = repository.getMovies()

        // Assert
        assertEquals(1, result.size)
        assertEquals("Titulo de Teste", result[0].title)
        assertEquals("Descricao", result[0].overview)
        assertEquals("https://image.tmdb.org/t/p/w500/poster.jpg", result[0].posterPath)
    }

    @Test
    fun `getMovies retorna lista vazia quando falha`() = runTest {
        // Arrange
        val errorResponse = Response.error<MovieResponse>(
            401,
            ResponseBody.create("application/json".toMediaTypeOrNull(), "{}")
        )

        whenever(api.getDay(any())).thenReturn(errorResponse)

        val result: List<Movie> = repository.getMovies()

        // Assert
        assertEquals(emptyList<Movie>(), result)
    }
}
