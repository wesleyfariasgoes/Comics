package com.example.comics.presentation.state

import com.example.comics.domain.model.Movie

data class MovieState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)