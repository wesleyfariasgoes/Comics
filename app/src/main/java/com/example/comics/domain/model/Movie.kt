package com.example.comics.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    val posterPath: String
)
