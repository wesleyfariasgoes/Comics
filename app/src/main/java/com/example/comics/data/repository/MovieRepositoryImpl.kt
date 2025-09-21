package com.example.comics.data.repository

import com.example.comics.BuildConfig
import com.example.comics.data.api.Api
import com.example.comics.domain.model.Movie
import com.example.comics.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: Api
) : MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        val token = BuildConfig.API_TOKEN

        return withContext(Dispatchers.IO) {
            val response = api.getDay(token)
            if (response.isSuccessful) {
                response.body()?.results?.map { movieDto ->
                    Movie(
                        id = movieDto.id,
                        title = movieDto.title,
                        overview = movieDto.overview,
                        posterPath = movieDto.getPosterUrl()
                    )
                } ?: emptyList()
            } else {
                emptyList()
            }
        }
    }

}