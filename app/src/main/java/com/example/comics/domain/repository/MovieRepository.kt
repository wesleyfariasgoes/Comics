package com.example.comics.domain.repository

import com.example.comics.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
}