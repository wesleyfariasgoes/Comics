package com.example.comics.data.mapper

import com.example.comics.data.model.MovieDto
import com.example.comics.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    val imageUrl = posterPath.let { "https://image.tmdb.org/t/p/w500$it" }

    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = imageUrl
    )
}
