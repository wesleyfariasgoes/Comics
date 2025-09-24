package com.example.comics.data.repository

import com.example.comics.BuildConfig
import com.example.comics.data.api.Api
import com.example.comics.data.mapper.toDomain
import com.example.comics.data.model.PaginatedMovies
import com.example.comics.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: Api,
) : MovieRepository {
    val token = BuildConfig.API_TOKEN
    override suspend fun getMovies(page: Int): PaginatedMovies {
        val response = api.getDay(token = token,page = page, language = "pt-BR")
        val movies = response.results.map { it.toDomain() }

        return PaginatedMovies(
            movies = movies,
            totalPages = response.totalPages
        )
    }
}