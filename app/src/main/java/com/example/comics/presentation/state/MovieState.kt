package com.example.comics.presentation.state

import com.example.comics.domain.model.Movie

data class MovieState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList()
)