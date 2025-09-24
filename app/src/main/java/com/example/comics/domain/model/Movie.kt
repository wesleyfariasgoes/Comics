package com.example.comics.domain.model


import com.example.comics.domain.model.Movie.Companion.sanitizeString
import java.io.Serializable


data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val posterPath: String
): Serializable{
    companion object {
        fun sanitizeString(input: String?): String? {
            return input?.replace("+", " ")
                ?.replace(":%20", ": ")
        }
    }
}

fun Movie.toMovie(): Movie {

    return Movie(
        id = this.id,
        title = sanitizeString(this.title),
        overview = sanitizeString(this.overview),
        posterPath = posterPath
    )
}