package com.example.comics.data.model

import com.example.comics.domain.model.Movie

data class PaginatedMovies(
    val movies: List<Movie>,
    val totalPages: Int
)
