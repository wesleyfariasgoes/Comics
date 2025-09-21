package com.example.comics.presentation.state

import com.example.comics.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviesState(
    val movies: Flow<MovieResponse> = emptyFlow()
)