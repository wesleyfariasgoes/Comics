package com.example.comics.domain.repository

import com.example.comics.data.model.PaginatedMovies

interface MovieRepository {
    suspend fun getMovies(page: Int): PaginatedMovies
}